package com.gzy.leeboo.controller;

import com.gzy.leeboo.entity.Punishment;
import com.gzy.leeboo.entity.Reward;
import com.gzy.leeboo.service.RewAndPuniService;
import com.gzy.leeboo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/salary/rewAndPuni")
public class RewAndPuniController {
    private RewAndPuniService rewAndPuniService;

    @Autowired
    public void setRewAndPuniService(RewAndPuniService rewAndPuniService) {
        this.rewAndPuniService = rewAndPuniService;
    }

    @GetMapping("/getAllRewardsAndPunishments")
    public Result getAllRewardsAndPunishments() {
        return Result.success().data("rewards", rewAndPuniService.getAllRewards()).data("punishments", rewAndPuniService.getAllPunishments());
    }

    @PostMapping("/addReward")
    public Result addReward(@RequestBody Reward reward) {
        return rewAndPuniService.addReward(reward) ? Result.success().message("添加成功！") : Result.failure().message("添加失败！");
    }

    @PostMapping("/addPunishment")
    public Result addPunishment(@RequestBody Punishment punishment) {
        return rewAndPuniService.addPunishment(punishment) ? Result.success().message("添加成功！") : Result.failure().message("添加失败！");
    }

    @PostMapping("/addEmployeeReward/{employeeId}/{rewardId}")
    public Result addEmployeeReward(@PathVariable("employeeId") Integer employeeId, @PathVariable("rewardId") Integer rewardId) {
        return rewAndPuniService.addEmployeeReward(employeeId, rewardId) ? Result.success().message("添加成功！") : Result.failure().message("添加失败！");
    }

    @PostMapping("/addEmployeePunishment/{employeeId}/{punishmentId}")
    public Result addEmployeePunishment(@PathVariable("employeeId")Integer employeeId, @PathVariable("punishmentId") Integer punishmentId) {
        return rewAndPuniService.addEmployeePunishment(employeeId, punishmentId) ? Result.success().message("添加成功！") : Result.failure().message("添加失败！");
    }

    @DeleteMapping("/deleteRewardById/{id}")
    public Result deleteRewardById(@PathVariable("id") Integer id) {
        return rewAndPuniService.deleteRewardById(id) ? Result.success().message("删除成功！") : Result.failure().message("删除是失败！");
    }

    @DeleteMapping("/deletePunishmentById/{id}")
    public Result deletePunishmentById(@PathVariable("id") Integer id) {
        return rewAndPuniService.deletePunishmentById(id) ? Result.success().message("删除成功！") : Result.failure().message("删除是失败！");
    }

    @DeleteMapping("/deleteEmployeeReward/{employeeId}/{rewardId}")
    public Result deleteEmployeeReward(@PathVariable("employeeId") Integer employeeId, @PathVariable("rewardId") Integer rewardId) {
        return rewAndPuniService.deleteEmployeeReward(employeeId, rewardId) ? Result.success() : Result.failure();
    }

    @DeleteMapping("/deleteEmployeePunishment/{employeeId}/{punishmentId}")
    public Result deleteEmployeePunishment(@PathVariable("employeeId") Integer employeeId, @PathVariable("punishmentId") Integer punishmentId) {
        return rewAndPuniService.deleteEmployeePunishment(employeeId, punishmentId)  ? Result.success() : Result.failure();
    }
}
