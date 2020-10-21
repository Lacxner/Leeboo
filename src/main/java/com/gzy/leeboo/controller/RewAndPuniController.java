package com.gzy.leeboo.controller;

import com.gzy.leeboo.config.validator.group.Add;
import com.gzy.leeboo.config.validator.group.Delete;
import com.gzy.leeboo.entity.Punishment;
import com.gzy.leeboo.entity.Reward;
import com.gzy.leeboo.service.EmployeeService;
import com.gzy.leeboo.service.RewAndPuniService;
import com.gzy.leeboo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@RestController
@RequestMapping("/salary/rewAndPuni")
public class RewAndPuniController {
    private RewAndPuniService rewAndPuniService;
    private EmployeeService employeeService;

    @Autowired
    public void setRewAndPuniService(RewAndPuniService rewAndPuniService) {
        this.rewAndPuniService = rewAndPuniService;
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/getAllRewardsAndPunishments")
    public Result getAllRewardsAndPunishments() {
        return Result.success()
                .data("rewards", rewAndPuniService.getAllRewards())
                .data("punishments", rewAndPuniService.getAllPunishments());
    }

    @GetMapping("/getRewardsAndPunishmentsByEmployeeName/{name}")
    public Result getRewardsAndPunishmentsByEmployeeName(
            @PathVariable("name")
            @Pattern(regexp = "^[\\u4e00-\\u9fa5]{2,5}$", message = "员工姓名格式不正确！") String name) {
        if ("null".equals(name)) {
            name = null;
        }
        Integer id = employeeService.getEmployeeIdByName(name);
        if (name == null || id == null) {
            return Result.failure().message("未查询到此员工！");
        }
        return Result.success()
                .data("employeeRewards", rewAndPuniService.getRewardsByEmployeeName(name))
                .data("employeePunishments", rewAndPuniService.getPunishmentsByEmployeeName(name))
                .data("id", id);
    }

    @PostMapping("/addReward")
    public Result addReward(@RequestBody @Validated(Add.class) Reward reward) {
        return rewAndPuniService.addReward(reward) ? Result.success().message("添加成功！") : Result.failure().message("添加失败！");
    }

    @PostMapping("/addPunishment")
    public Result addPunishment(@RequestBody @Validated(Add.class) Punishment punishment) {
        return rewAndPuniService.addPunishment(punishment) ? Result.success().message("添加成功！") : Result.failure().message("添加失败！");
    }

    @PostMapping("/addEmployeeReward/{employeeId}/{rewardId}")
    public Result addEmployeeReward(
            @PathVariable("employeeId") @NotNull @Min(1) Integer employeeId,
            @PathVariable("rewardId") @NotNull @Min(1) Integer rewardId) {
        return rewAndPuniService.addEmployeeReward(employeeId, rewardId) ? Result.success().message("添加成功！") : Result.failure().message("添加失败！");
    }

    @PostMapping("/addEmployeePunishment/{employeeId}/{punishmentId}")
    public Result addEmployeePunishment(
            @PathVariable("employeeId") @NotNull @Min(1) Integer employeeId,
            @PathVariable("punishmentId") @NotNull @Min(1) Integer punishmentId) {
        return rewAndPuniService.addEmployeePunishment(employeeId, punishmentId) ? Result.success().message("添加成功！") : Result.failure().message("添加失败！");
    }

    @DeleteMapping("/deleteRewardById/{id}")
    public Result deleteRewardById(@PathVariable("id") @NotNull @Min(1) Integer id) {
        if (employeeService.getEmployeeCountsByRewardId(id) > 0) {
            return Result.failure().message("该奖励已经被员工关联，请先删除员工！");
        }
        return rewAndPuniService.deleteRewardById(id) ? Result.success().message("删除成功！") : Result.failure().message("删除失败！");
    }

    @DeleteMapping("/deletePunishmentById/{id}")
    public Result deletePunishmentById(@PathVariable("id") @NotNull @Min(1) Integer id) {
        if (employeeService.getEmployeeCountsByPunishmentId(id) > 0) {
            return Result.failure().message("该处罚已经被员工关联，请先删除员工！");
        }
        return rewAndPuniService.deletePunishmentById(id) ? Result.success().message("删除成功！") : Result.failure().message("删除失败！");
    }

    @DeleteMapping("/removeEmployeeReward/{employeeId}")
    public synchronized Result removeEmployeeReward(
            @PathVariable("employeeId") @NotNull @Min(1) Integer employeeId,
            @RequestBody @Validated(Delete.class) Reward reward) {
        return rewAndPuniService.removeEmployeeReward(employeeId, reward) ? Result.success() : Result.failure();
    }

    @DeleteMapping("/removeEmployeePunishment/{employeeId}")
    public synchronized Result removeEmployeePunishment(
            @PathVariable("employeeId") @NotNull @Min(1) Integer employeeId,
            @RequestBody @Validated(Delete.class) Punishment punishment) {
        return rewAndPuniService.removeEmployeePunishment(employeeId, punishment)  ? Result.success() : Result.failure();
    }
}
