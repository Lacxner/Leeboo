package com.gzy.leeboo.controller;

import com.gzy.leeboo.config.validator.group.Add;
import com.gzy.leeboo.entity.Department;
import com.gzy.leeboo.service.DepartmentService;
import com.gzy.leeboo.service.EmployeeService;
import com.gzy.leeboo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@RequestMapping("/system/basic")
@RestController
public class DepartmentController {
    private DepartmentService departmentService;
    private EmployeeService employeeService;

    @Autowired
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/getAllDepartments")
    public Result getAllDepartments() {
        return Result.success().data("items", departmentService.getAllDepartments());
    }

    @PostMapping("/addDepartment")
    public Result addDepartment(@RequestBody @Validated(Add.class) Department department) {
        return departmentService.addDepartment(department) ? Result.success().message("添加成功！") : Result.success().message("添加失败！");
    }

    @DeleteMapping("/deleteDepartmentById/{id}/{parentId}")
    public Result deleteDepartmentById(@PathVariable("id") @Min(1) @NotNull Integer id, @Min(-1) @NotNull @PathVariable("parentId") Integer parentId) {
        if (employeeService.getEmployeeCountsByDepartmentId(id) > 0) {
            return Result.failure().message("该部门已经被员工关联，请先删除员工！");
        }
        return departmentService.deleteDepartmentById(id, parentId) ? Result.success().message("删除成功！") : Result.failure().message("删除失败！");
    }

    @DeleteMapping("/deleteBatchDepartmentByIds/{parentId}")
    public Result deleteBatchDepartmentByIds(@RequestBody @NotEmpty List<Integer> ids, @Min(1) @NotNull @PathVariable("parentId") Integer parentId) {
        if (employeeService.getEmployeeCountsByDepartmentIds(ids) > 0) {
            return Result.failure().message("其中的某个部门已经被员工关联，请先删除员工！");
        }
        return departmentService.deleteBatchDepartmentByIds(ids, parentId) ? Result.success().message("删除成功！") : Result.failure().message("删除失败！");
    }
}
