package com.gzy.leeboo.controller;

import com.gzy.leeboo.config.validator.group.Add;
import com.gzy.leeboo.config.validator.group.UpdateEnabled;
import com.gzy.leeboo.config.validator.group.UpdateName;
import com.gzy.leeboo.entity.Position;
import com.gzy.leeboo.service.EmployeeService;
import com.gzy.leeboo.service.PositionService;
import com.gzy.leeboo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/system/basic")
public class PositionController {
    private PositionService positionService;
    private EmployeeService employeeService;

    @Autowired
    public void setPositionService(PositionService positionService) {
        this.positionService = positionService;
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/getAllPositions")
    public Result getAllPositions() {
        return Result.success().data("items", positionService.getAllPositions());
    }

    @DeleteMapping("/deletePositionById/{id}")
    public Result deletePositionById(@PathVariable("id") @NotNull @Min(1) Integer id) {
        if (employeeService.getEmployeeCountsByPositionId(id) > 0) {
            return Result.failure().message("该职位已经被员工关联，请先删除员工！");
        }
        return positionService.deletePositionById(id) ? Result.success().message("删除成功！") : Result.failure().message("删除失败！");
    }

    @DeleteMapping("/deleteBatchPositionByIds")
    public Result deleteBatchPositionByIds(@RequestBody @NotEmpty List<Integer> ids) {
        if (employeeService.getEmployeeCountsByPositionIds(ids) > 0) {
            return Result.failure().message("其中的某个职位已经被员工关联，请先删除员工！");
        }
        return positionService.deleteBatchPositionByIds(ids) ? Result.success().message("删除成功！") : Result.failure().message("删除失败！");
    }

    @PostMapping("/addPosition")
    public Result addPosition(@RequestBody @Validated(Add.class) Position position) {
        return positionService.addPosition(position) ? Result.success().message("添加成功！") : Result.failure().message("添加失败！");
    }

    @PutMapping("/updatePositionEnabled")
    public Result updatePositionEnabled(@RequestBody @Validated(UpdateEnabled.class) Position position) {
        return positionService.updatePositionEnabled(position) ? Result.success() : Result.failure().message("修改失败！");
    }

    @PutMapping("/updatePositionName")
    public Result updatePositionName(@RequestBody @Validated(UpdateName.class) Position position) {
        return positionService.updatePositionName(position) ? Result.success().message("修改成功！") : Result.failure().message("修改失败！");
    }
}
