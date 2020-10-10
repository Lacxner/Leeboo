package com.gzy.leeboo.controller;

import com.gzy.leeboo.entity.Salary;
import com.gzy.leeboo.service.SalaryService;
import com.gzy.leeboo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/salary/search")
public class SalarySearchController {
    private SalaryService salaryService;

    @Autowired
    public void setSalaryService(SalaryService salaryService) {
        this.salaryService  = salaryService;
    }

    @GetMapping("/getSalaryByName/{name}")
    public Result getSalaryByName(@PathVariable("name") String name) {
        if ("null".equals(name)) {
            name = null;
        }
        Salary salary = salaryService.getSalaryByName(name);
        return salary != null ? Result.success().data("item", salary) : Result.failure().message("未查询到此员工！");
    }
}
