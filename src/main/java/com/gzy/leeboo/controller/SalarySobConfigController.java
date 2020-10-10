package com.gzy.leeboo.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gzy.leeboo.dto.EmployeeSalary;
import com.gzy.leeboo.service.SalaryService;
import com.gzy.leeboo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/salary/setOfBooksConfig")
@RestController
public class SalarySobConfigController {
    private SalaryService salaryService;

    @Autowired
    public void setSalaryService(SalaryService salaryService) {
        this.salaryService = salaryService;
    }

    @GetMapping("/getAllEmployeeSalaryByName/{name}/{currentPage}/{pageSize}")
    public Result getAllEmployeeSalaryByName(@PathVariable("name") String name, @PathVariable("currentPage") Integer currentPage, @PathVariable("pageSize") Integer pageSize) {
        if ("null".equals(name)) {
            name = null;
        }
        Page<EmployeeSalary> page = PageHelper.startPage(currentPage, pageSize, true, true, true);
        List<EmployeeSalary> allEmployeeSalary = salaryService.getAllEmployeeSalaryByName(name);
        Long total = page.getTotal();
        return Result.success().data("items", allEmployeeSalary).data("total", total);
    }

    @GetMapping("/getAllBasicSalary")
    public Result getAllBasicSalary() {
        return Result.success().data("items", salaryService.getAllBasicSalary());
    }

    @PutMapping("/updateEmployeeSalary/{salaryId}/{employeeId}")
    public Result updateEmployeeSalary(@PathVariable("salaryId") Integer salaryId, @PathVariable("employeeId") Integer employeeId) {
        return salaryService.updateEmployeeSalary(salaryId, employeeId) ? Result.success().message("修改成功！") : Result.failure().message("修改失败！");
    }

    @PostMapping("/addEmployeeSalary/{salaryId}/{employeeId}")
    public Result addEmployeeSalary(@PathVariable("salaryId") Integer salaryId, @PathVariable("employeeId") Integer employeeId) {
        return salaryService.addEmployeeSalary(salaryId, employeeId) ? Result.success().message("添加成功！") : Result.failure().message("添加失败！");
    }
}
