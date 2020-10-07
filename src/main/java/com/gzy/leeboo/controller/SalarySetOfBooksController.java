package com.gzy.leeboo.controller;

import com.gzy.leeboo.entity.Salary;
import com.gzy.leeboo.service.EmployeeService;
import com.gzy.leeboo.service.SalaryService;
import com.gzy.leeboo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salary/setOfBooks")
public class SalarySetOfBooksController {
    private SalaryService salaryService;
    private EmployeeService employeeService;

    @Autowired
    public void setSalaryService(SalaryService salaryService) {
        this.salaryService = salaryService;
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/getAllSalary")
    public Result getAllSalary() {
        return Result.success().data("items", salaryService.getAllSalary());
    }

    @PostMapping("/addSalary")
    public Result addSalary(@RequestBody Salary salary) {
        return salaryService.addSalary(salary) ? Result.success().message("添加成功！") : Result.failure().message("添加失败！");
    }

    @PutMapping("/updateSalary")
    public Result updateSalary(@RequestBody Salary salary) {
        return salaryService.updateSalary(salary) ? Result.success().message("修改成功！") : Result.failure().message("修改失败！");
    }

    @DeleteMapping("/deleteSalaryById/{id}")
    public Result deleteSalaryById(@PathVariable("id") Integer id) {
        if (employeeService.getEmployeeCountsBySalaryId(id) > 0) {
            return Result.failure().message("该工资账套已经被员工关联，请先删除员工！");
        }
        return salaryService.deleteSalaryById(id) ? Result.success().message("删除成功！") : Result.failure().message("删除失败！");
    }

    @DeleteMapping("/deleteBatchSalaryByIds")
    public Result deleteBatchSalaryByIds(@RequestBody List<Integer> ids) {
        if (employeeService.getEmployeeCountsBySalaryIds(ids) > 0) {
            return Result.failure().message("其中的某个工资账套已经被员工关联，请先删除员工！");
        }
        return salaryService.deleteBatchSalaryByIds(ids) ? Result.success().message("删除成功！") : Result.failure().message("删除失败！");
    }
}
