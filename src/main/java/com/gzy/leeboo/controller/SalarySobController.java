package com.gzy.leeboo.controller;

import com.gzy.leeboo.config.validator.group.Add;
import com.gzy.leeboo.config.validator.group.Update;
import com.gzy.leeboo.entity.SalarySob;
import com.gzy.leeboo.service.EmployeeService;
import com.gzy.leeboo.service.SalarySobService;
import com.gzy.leeboo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/salary/sob")
public class SalarySobController {
    private SalarySobService salarySobService;
    private EmployeeService employeeService;

    @Autowired
    public void setSalarySobService(SalarySobService salarySobService) {
        this.salarySobService = salarySobService;
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/getAllSalarySob")
    public Result getAllSalarySob() {
        return Result.success().data("items", salarySobService.getAllSalarySob());
    }

    @PostMapping("/addSalarySob")
    public Result addSalarySob(@RequestBody @Validated(Add.class) SalarySob salarySob) {
        return salarySobService.addSalarySob(salarySob) ? Result.success().message("添加成功！") : Result.failure().message("添加失败！");
    }

    @PutMapping("/updateSalarySob")
    public Result updateSalarySob(@RequestBody @Validated(Update.class) SalarySob salarySob) {
        return salarySobService.updateSalarySob(salarySob) ? Result.success().message("修改成功！") : Result.failure().message("修改失败！");
    }

    @DeleteMapping("/deleteSalarySobById/{id}")
    public Result deleteSalarySobById(@PathVariable("id") @NotNull @Min(1) Integer id) {
        if (employeeService.getEmployeeCountsBySalarySobId(id) > 0) {
            return Result.failure().message("该工资账套已经被员工关联，请先删除员工！");
        }
        return salarySobService.deleteSalarySobById(id) ? Result.success().message("删除成功！") : Result.failure().message("删除失败！");
    }

    @DeleteMapping("/deleteBatchSalarySobByIds")
    public Result deleteBatchSalarySobByIds(@RequestBody @NotEmpty List<Integer> ids) {
        if (employeeService.getEmployeeCountsBySalarySobIds(ids) > 0) {
            return Result.failure().message("其中的某个工资账套已经被员工关联，请先删除员工！");
        }
        return salarySobService.deleteBatchSalarySobByIds(ids) ? Result.success().message("删除成功！") : Result.failure().message("删除失败！");
    }
}
