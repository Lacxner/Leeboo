package com.gzy.leeboo.controller;

import com.gzy.leeboo.config.validator.group.Add;
import com.gzy.leeboo.config.validator.group.Update;
import com.gzy.leeboo.config.validator.group.UpdateEnabled;
import com.gzy.leeboo.entity.Rank;
import com.gzy.leeboo.service.EmployeeService;
import com.gzy.leeboo.service.RankService;
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
public class RankController {
    private RankService rankService;
    private EmployeeService employeeService;

    @Autowired
    public void setRankService(RankService rankService) {
        this.rankService = rankService;
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/getAllRanks")
    public Result getAllRanks() {
        return Result.success().data("items", rankService.getAllRanks());
    }

    @DeleteMapping("/deleteRankById/{id}")
    public Result deleteRankById(@PathVariable("id") @NotNull @Min(1) Integer id) {
        if (employeeService.getEmployeeCountsByRankId(id) > 0) {
            return Result.failure().message("该职称已经被员工关联，请先删除员工！");
        }
        return rankService.deleteRankById(id) ? Result.success().message("删除成功！") : Result.failure().message("删除失败！");
    }

    @DeleteMapping("/deleteBatchRankByIds")
    public Result deleteBatchRankByIds(@RequestBody @NotEmpty List<Integer> ids) {
        if (employeeService.getEmployeeCountsByRankIds(ids) > 0) {
            return Result.failure().message("其中的某个职称已经被员工关联，请先删除员工！");
        }
        return rankService.deleteBatchRankByIds(ids) ? Result.success().message("删除成功！") : Result.failure().message("删除失败！");
    }

    @PostMapping("/addRank")
    public Result addRank(@RequestBody @Validated(Add.class) Rank rank) {
        return rankService.addRank(rank) ? Result.success().message("添加成功！") : Result.failure().message("添加失败！");
    }

    @PutMapping("/updateRank")
    public Result updateRank(@RequestBody @Validated(Update.class) Rank rank) {
        return rankService.updateRank(rank) ? Result.success().message(("修改成功！")) : Result.failure().message("修改失败！");
    }

    @PutMapping("/updateRankEnabled")
    public Result updateRankEnabled(@RequestBody @Validated(UpdateEnabled.class) Rank rank) {
        return rankService.updateRankEnabled(rank) ? Result.success() : Result.failure().message("修改失败！");
    }
}
