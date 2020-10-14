package com.gzy.leeboo.controller;

import com.gzy.leeboo.dto.EmployeeMove;
import com.gzy.leeboo.service.*;
import com.gzy.leeboo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee/move")
public class EmployeeMoveController {
    private EmployeeMoveService employeeMoveService;
    private EmployeeService employeeService;
    private DepartmentService departmentService;
    private PositionService positionService;
    private RankService rankService;

    @Autowired
    public void setEmployeeMoveService(EmployeeMoveService employeeMoveService) {
        this.employeeMoveService = employeeMoveService;
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Autowired
    public void setPositionService(PositionService positionService) {
        this.positionService = positionService;
    }

    @Autowired
    public void setRankService(RankService rankService) {
        this.rankService = rankService;
    }

    @GetMapping("/initBasicOptions")
    public Result initBasicOptions() {
        return Result.success()
                .data("departments", departmentService.getAllDepartments())
                .data("positions", positionService.getAllPositions())
                .data("ranks", rankService.getAllRanks());
    }

    @GetMapping("/getEmployeeMoveByName/{name}")
    public Result getEmployeeMoveByName(@PathVariable("name") String name) {
        if ("null".equals(name)) {
            name = null;
        }
        if (name == null || employeeService.getEmployeeIdByName(name) == null) {
            return Result.failure().message("未查询到此员工！");
        }
        return Result.success().data("item", employeeMoveService.getEmployeeMoveByName(name));
    }

    @PutMapping("/moveEmployee")
    public Result moveEmployee(@RequestBody EmployeeMove employeeMove) {
        return employeeMoveService.moveEmployee(employeeMove) ? Result.success().message("修改成功！") : Result.failure().message("修改失败！");
    }
}
