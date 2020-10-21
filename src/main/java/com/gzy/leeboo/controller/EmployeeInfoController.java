package com.gzy.leeboo.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gzy.leeboo.config.validator.group.Add;
import com.gzy.leeboo.config.validator.group.AddEmployee;
import com.gzy.leeboo.config.validator.group.Update;
import com.gzy.leeboo.config.validator.group.UpdateEmployee;
import com.gzy.leeboo.dto.SearchEmployee;
import com.gzy.leeboo.entity.Employee;
import com.gzy.leeboo.service.*;
import com.gzy.leeboo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

@RequestMapping("/employee/info")
@RestController
public class EmployeeInfoController {
    private EmployeeService employeeService;
    private NationService nationService;
    private PoliticsService politicsService;
    private PositionService positionService;
    private DepartmentService departmentService;
    private RankService rankService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setNationService(NationService nationService) {
        this.nationService = nationService;
    }

    @Autowired
    public void setPoliticsService(PoliticsService politicsService) {
        this.politicsService = politicsService;
    }

    @Autowired
    public void setPositionService(PositionService positionService) {
        this.positionService = positionService;
    }

    @Autowired
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Autowired
    public void setRankService(RankService rankService) {
        this.rankService = rankService;
    }

    @GetMapping("/getAllEmployees/{currentPage}/{pageSize}")
    public Result getAllEmployees(
            @PathVariable("currentPage") @NotNull @Min(1) Integer currentPage,
            @PathVariable("pageSize") @NotNull @Min(1) Integer pageSize) {
        Page<Employee> page = PageHelper.startPage(currentPage, pageSize, true, true, true);
        List<Employee> allEmployees = employeeService.getAllEmployees();
        Long total = page.getTotal();
        return Result.success().data("items", allEmployees).data("total", total);
    }

    @PostMapping("/searchEmployees/{currentPage}/{pageSize}")
    public Result searchEmployees(
            @RequestBody @Validated SearchEmployee searchEmployee,
            @PathVariable("currentPage") @NotNull @Min(1) Integer currentPage,
            @PathVariable("pageSize") @NotNull @Min(1) Integer pageSize) {
        // 格式化工号
        if (searchEmployee.getWorkID() != null && !searchEmployee.getWorkID().isEmpty()) {
            searchEmployee.setWorkID(String.format("%08d", Integer.parseInt(searchEmployee.getWorkID())));
        }

        Page<Employee> page = PageHelper.startPage(currentPage, pageSize, true, true, true);
        List<Employee> employees = employeeService.searchEmployees(searchEmployee);
        Long total = page.getTotal();
        return Result.success().data("items", employees).data("total", total);
    }

    @GetMapping("/initBasicOptions")
    public Result initBasicOptions() {
        return Result.success()
                .data("nations", nationService.getAllNations())
                .data("positions", positionService.getAllPositions())
                .data("departments", departmentService.getAllDepartments())
                .data("politics", politicsService.getAllPolitics())
                .data("ranks", rankService.getAllRanks());
    }

    @GetMapping("/getMaxWorkID")
    public Result getMaxWorkID() {
        return Result.success().data("item", String.format("%08d", employeeService.getMaxWorkID() + 1));
    }

    @PostMapping("/addEmployee")
    public Result addEmployee(@RequestBody @Validated(AddEmployee.class) Employee employee) {
        return employeeService.addEmployee(employee) ? Result.success().message("添加成功！") : Result.failure().message("添加失败！");
    }

    @PutMapping("/updateEmployee")
    public Result updateEmployee(@RequestBody @Validated(UpdateEmployee.class) Employee employee) {
        return employeeService.updateEmployee(employee) ? Result.success().message("修改成功！") : Result.failure().message("修改失败！");
    }

    @DeleteMapping("/deleteEmployeeById/{id}")
    public Result deleteEmployeeById(@PathVariable("id") @NotNull @Min(1) Integer id) {
        return employeeService.deleteEmployeeById(id) ? Result.success().message("删除成功！") : Result.failure().message("删除失败！");
    }

    @DeleteMapping("/deleteBatchEmployeeByIds")
    public Result deleteBatchEmployeeByIds(@RequestBody @NotEmpty List<Integer> ids) {
        return employeeService.deleteBatchEmployeeByIds(ids) ? Result.success().message("删除成功！") : Result.failure().message("删除失败！");
    }

    /**
     * 通过 EasyExcel 解析上传的 Excel 文件
     * @param file MultipartFile参数名必须和前端上传文件名相同，这样Spring MVC才能将这二者进行绑定
     * @return 上传结果
     */
    @PostMapping("/upload")
    public void upload(@RequestParam("file") @NotNull MultipartFile file) throws FileNotFoundException {
        if ("".equals(file) || file.getSize() <= 0) {
            throw new FileNotFoundException();
        }
        employeeService.upload(file);
    }

    /**
     * EasyExcel 官方文档：https://www.yuque.com/easyexcel/doc/easyexcel
     * application/octet-stream 表示响应体内容是一个二进制文件
     * Content-Disposition：
     *  - inline：表示浏览器内嵌显示该文件。
     *  - attachment：表示浏览器会以附件的形式下载该文件。
     * @param response Http响应
     */
    @GetMapping("/download")
    public void download(HttpServletResponse response) {
        try {
            // 设置文件附件下载的响应头
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("员工信息表", "utf-8") + ".xlsx");
            // 导出操作
            employeeService.download(response);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/downloadTemplate")
    public void downloadTemplate(HttpServletResponse response) {
        try {
            // 设置文件附件下载的响应头
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("模板", "utf-8") + ".xlsx");
            // 导出模板操作
            employeeService.downloadTemplate(response);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
