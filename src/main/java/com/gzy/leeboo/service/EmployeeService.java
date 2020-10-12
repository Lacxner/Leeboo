package com.gzy.leeboo.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.gzy.leeboo.dto.SearchEmployee;
import com.gzy.leeboo.entity.Employee;
import com.gzy.leeboo.mapper.*;
import com.gzy.leeboo.utils.easyExcel.listener.EmployeeListener;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class EmployeeService {
    private EmployeeMapper employeeMapper;
    private NationMapper nationMapper;
    private PoliticsMapper politicsMapper;
    private DepartmentMapper departmentMapper;
    private RankMapper rankMapper;
    private PositionMapper positionMapper;
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public void setEmployeeMapper(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    @Autowired
    public void setNationMapper(NationMapper nationMapper) {
        this.nationMapper = nationMapper;
    }

    @Autowired
    public void setPoliticsMapper(PoliticsMapper politicsMapper) {
        this.politicsMapper = politicsMapper;
    }

    @Autowired
    public void setDepartmentMapper(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    @Autowired
    public void setRankMapper(RankMapper rankMapper) {
        this.rankMapper = rankMapper;
    }

    @Autowired
    public void setPositionMapper(PositionMapper positionMapper) {
        this.positionMapper = positionMapper;
    }

    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public List<Employee> getAllEmployees() {
        return employeeMapper.getAllEmployees();
    }

    public List<Employee> searchEmployees(SearchEmployee searchEmployee) {
        return employeeMapper.searchEmployees(searchEmployee);
    }

    public Integer getEmployeeIdByName(String name) {
        return employeeMapper.getEmployeeIdByName(name);
    }

    public Integer getMaxWorkID() {
        return employeeMapper.getMaxWorkID();
    }

    public Integer getEmployeeCountsByDepartmentId(Integer departmentId) {
        return employeeMapper.getEmployeeCountsByDepartmentId(departmentId);
    }

    public Integer getEmployeeCountsByDepartmentIds(List<Integer> departmentIds) {
        return employeeMapper.getEmployeeCountsByDepartmentIds(departmentIds);
    }

    public Integer getEmployeeCountsByPositionId(Integer positionId) {
        return employeeMapper.getEmployeeCountsByPositionId(positionId);
    }

    public Integer getEmployeeCountsByPositionIds(List<Integer> positionIds) {
        return employeeMapper.getEmployeeCountsByPositionIds(positionIds);
    }

    public Integer getEmployeeCountsByRankId(Integer rankId) {
        return employeeMapper.getEmployeeCountsByRankId(rankId);
    }

    public Integer getEmployeeCountsByRankIds(List<Integer> rankIds) {
        return employeeMapper.getEmployeeCountsByRankIds(rankIds);
    }

    public Integer getEmployeeCountsBySalarySobId(Integer salarySobId) {
        return employeeMapper.getEmployeeCountsBySalarySobId(salarySobId);
    }

    public Integer getEmployeeCountsBySalarySobIds(List<Integer> salarySobIds) {
        return employeeMapper.getEmployeeCountsBySalarySobIds(salarySobIds);
    }

    public Integer getEmployeeCountsByRewardId(Integer rewardId) {
        return employeeMapper.getEmployeeCountsByRewardId(rewardId);
    }

    public Integer getEmployeeCountsByPunishmentId(Integer punishmentId) {
        return employeeMapper.getEmployeeCountsByPunishmentId(punishmentId);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public Boolean addEmployee(Employee employee) {
        if (!employeeMapper.addEmployee(employee)) {
            return false;
        }
        // 发送入职邮件
        Employee intactEmployee = employeeMapper.getEmployeeById(employee.getId());
        rabbitTemplate.convertAndSend("mail.welcome", intactEmployee);
        return true;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public Boolean addBatchEmployees(List<Employee> employees) {
        if (!employeeMapper.addBatchEmployees(employees)) {
            return false;
        }
        // 批量发送入职邮件
        for (Employee employee : employees) {
            Employee intactEmployee = employeeMapper.getEmployeeById(employee.getId());
            rabbitTemplate.convertAndSend("mail.welcome", intactEmployee);
        }
        return true;
    }

    public void upload(MultipartFile file) {
        BufferedInputStream bufferedInputStream = null;
        try {
            InputStream inputStream = file.getInputStream();
            bufferedInputStream = new BufferedInputStream(inputStream);
            EasyExcel.read(bufferedInputStream, Employee.class, new EmployeeListener(employeeMapper, nationMapper, politicsMapper, departmentMapper, rankMapper, positionMapper))
                    .sheet()
                    .doRead();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭外层流的同时会自动关闭节点流
                if (bufferedInputStream != null) bufferedInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void download(HttpServletResponse response) {
        BufferedOutputStream bufferedOutputStream = null;
        try {
            // 要导出的数据
            List<Employee> employees = employeeMapper.getAllEmployees();
            /*
             * 写入数据
             * 转换器在实体类中指定了
             */
            ServletOutputStream outputStream = response.getOutputStream();
            bufferedOutputStream = new BufferedOutputStream(outputStream);
            EasyExcel.write(bufferedOutputStream, Employee.class)
                    .registerWriteHandler(getHorizontalCellStyleStrategy()) // 设置样式
                    .sheet("数据表")
                    .doWrite(employees);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedOutputStream != null) bufferedOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void downloadTemplate(HttpServletResponse response) {
        BufferedOutputStream bufferedOutputStream = null;
        BufferedInputStream bufferedInputStream = null;
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            bufferedOutputStream = new BufferedOutputStream(outputStream);
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("excel/employeeTemplate.xlsx");
            bufferedInputStream = new BufferedInputStream(inputStream);
            int length;
            byte[] buf = new byte[1024];
            while ((length = bufferedInputStream.read(buf)) != -1) {
                bufferedOutputStream.write(buf, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedInputStream != null) bufferedInputStream.close();
                if (bufferedOutputStream != null) bufferedOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public Boolean updateEmployee(Employee employee) {
        return employeeMapper.updateEmployee(employee);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public Boolean deleteEmployeeById(Integer id) {
        return employeeMapper.deleteEmployeeById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public Boolean deleteBatchEmployeeByIds(List<Integer> ids) {
        return employeeMapper.deleteBatchEmployeeByIds(ids);
    }

    /**
     * 自定义输出的 Excel 的样式
     * HorizontalCellStyleStrategy 实现了 WriteHandler 接口，用于设置 Excel 的样式
     * @return WriteHandler 的实现类
     */
    private HorizontalCellStyleStrategy getHorizontalCellStyleStrategy() {
        // 头样式
        WriteCellStyle headCellStyle = new WriteCellStyle();
        // 设置前景背景颜色
        headCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        // 设置居中对齐
        headCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        headCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // 设置字体
        WriteFont headFont = new WriteFont();
        headFont.setBold(true);
        headFont.setFontHeightInPoints((short) 14);
        headFont.setFontName("黑体");
        headCellStyle.setWriteFont(headFont);

        // 内容样式
        WriteCellStyle contentCellStyle = new WriteCellStyle();
        // 设置居中对齐
        contentCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        contentCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // 设置字体
        WriteFont contentFont = new WriteFont();
        contentFont.setFontName("黑体");
        contentCellStyle.setWriteFont(contentFont);
        return new HorizontalCellStyleStrategy(headCellStyle, contentCellStyle);
    }
}
