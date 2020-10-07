package com.gzy.leeboo.utils.easyExcel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.gzy.leeboo.entity.*;
import com.gzy.leeboo.mapper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * <h1>EasyExcel解析员工Excel的监听器</h1>
 * <br/>用于 EasyExcel 接受解析后的员工信息数据。
 * <br/>该监听器不能被 Spring 管理，要每次读取 Excel 都要 new，并且该监听器里面无法自动注入Bean，
 * 里面用到 Bean 可以构造方法传进去。
 */
public class EmployeeListener extends AnalysisEventListener<Employee> {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeListener.class);
    private static int count;

    // 批量处理数据的数量
    private static final int BATCH_COUNT = 10;
    // 用于分批处理数据
    private List<Employee> list = new ArrayList<>(BATCH_COUNT);
    private EmployeeMapper employeeMapper;

    private static List<Nation> allNations;
    private static List<Politics> allPolitics;
    private static List<Department> allBasicDepartments;
    private static List<Rank> allRanks;
    private static List<Position> allPositions;

    public EmployeeListener() {}

    public EmployeeListener(EmployeeMapper employeeMapper, NationMapper nationMapper, PoliticsMapper politicsMapper, DepartmentMapper departmentMapper, RankMapper rankMapper, PositionMapper positionMapper) {
        this.employeeMapper = employeeMapper;
        allNations = nationMapper.getAllNations();
        allPolitics = politicsMapper.getAllPolitics();
        allBasicDepartments = departmentMapper.getAllBasicDepartments();
        allRanks = rankMapper.getAllRanks();
        allPositions = positionMapper.getAllPositions();
    }

    /**
     * 每解析一条数据都会来调用此方法
     * @param data 每次解析出来的一条数据
     * @param context 当前操作的工作簿上下文
     */
    @Override
    public void invoke(Employee data, AnalysisContext context) {
        list.add(data);
        /*
         * 当该集合元素数量达到临界值 BATCH_COUNT 时进行一次持久化，然后再清空次集合。
         * 如果所有数据解析完成后，List中还有数据但未达到 BATCH_COUNT，则会交由 doAfterAllAnalysed 方法进行最后一次数据存储操作。
         */
        if (list.size() >= BATCH_COUNT) {
            addData();
            // 处理完后清除集合中的数据
            list.clear();
        }
    }

    /**
     * 当工作表中所有数据解析完成后，就会来调用此方法
     * @param context 当前操作的工作簿上下文
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存一次数据，确保最后遗留的数据也存储到数据库
        addData();
        LOGGER.info("Excel解析成功！共解析了 " + count + " 条数据");
        count = 0;
    }

    /**
     * 最后整理数据，并将数据添加进数据库
     */
    private void addData() {
        count++;
        ListIterator<Employee> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            Employee employee = listIterator.next();
            // 设置员工工号
            employee.setWorkID(String.format("%08d", employeeMapper.getMaxWorkID() + 1 + listIterator.previousIndex()));

            // 设置员工民族id
            for (Nation nation : allNations) {
                if (nation.getName().equals(employee.getNation().getName())) {
                    employee.getNation().setId(nation.getId());
                    break;
                }
            }

            // 设置员工政治面貌id
            for (Politics politics : allPolitics) {
                if (politics.getName().equals(employee.getPolitics().getName())) {
                    employee.getPolitics().setId(politics.getId());
                    break;
                }
            }

            // 设置员工部门id
            for (Department department : allBasicDepartments) {
                if (department.getName().equals(employee.getDepartment().getName())) {
                    employee.getDepartment().setId(department.getId());
                    break;
                }
            }

            // 设置员工职称id
            for (Rank rank : allRanks) {
                if (rank.getName().equals(employee.getRank().getName())) {
                    employee.getRank().setId(rank.getId());
                    break;
                }
            }

            // 设置员工职位id
            for (Position position : allPositions) {
                if (position.getName().equals(employee.getPosition().getName())) {
                    employee.getPosition().setId(position.getId());
                    break;
                }
            }
        }
        employeeMapper.addBatchEmployees(list);
    }
}
