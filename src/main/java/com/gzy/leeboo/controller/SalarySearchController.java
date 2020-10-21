package com.gzy.leeboo.controller;

import com.gzy.leeboo.entity.Salary;
import com.gzy.leeboo.service.EmployeeService;
import com.gzy.leeboo.service.SalarySearchService;
import com.gzy.leeboo.service.SalaryService;
import com.gzy.leeboo.service.SalarySobService;
import com.gzy.leeboo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;

@RestController
@RequestMapping("/salary/search")
public class SalarySearchController {
    private SalarySearchService salarySearchService;
    private EmployeeService employeeService;

    @Autowired
    public void setSalaryService(SalarySearchService salarySearchService) {
        this.salarySearchService  = salarySearchService;
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/getSalaryByEmployeeName/{name}")
    public Result getSalaryByEmployeeName(
            @PathVariable("name")
            @Pattern(regexp = "^[\\u4e00-\\u9fa5]{2,5}$", message = "员工姓名格式不正确！") String name) {
        if ("null".equals(name)) {
            name = null;
        }
        if (name == null || employeeService.getEmployeeIdByName(name) == null) {
            return Result.failure().message("未查询到此员工！");
        }
        Salary salary = salarySearchService.getSalaryByEmployeeName(name);
        return salary != null ? Result.success().data("item", salary) : Result.failure().message("此员工还未设置工资账套！");
    }
}
