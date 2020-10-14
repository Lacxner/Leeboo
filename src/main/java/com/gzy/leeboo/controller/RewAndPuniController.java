package com.gzy.leeboo.controller;

import com.gzy.leeboo.entity.Punishment;
import com.gzy.leeboo.entity.Reward;
import com.gzy.leeboo.service.EmployeeService;
import com.gzy.leeboo.service.RewAndPuniService;
import com.gzy.leeboo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return Result.success().data("rewards", rewAndPuniService.getAllRewards()).data("punishments", rewAndPuniService.getAllPunishments());
    }

    @GetMapping("/getRewardsAndPunishmentsByEmployeeName/{name}")
    public Result getRewardsAndPunishmentsByEmployeeName(@PathVariable("name") String name) {
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
        if (employeeService.getEmployeeCountsByRewardId(id) > 0) {
            return Result.failure().message("该奖励已经被员工关联，请先删除员工！");
        }
        return rewAndPuniService.deleteRewardById(id) ? Result.success().message("删除成功！") : Result.failure().message("删除失败！");
    }

    @DeleteMapping("/deletePunishmentById/{id}")
    public Result deletePunishmentById(@PathVariable("id") Integer id) {
        if (employeeService.getEmployeeCountsByPunishmentId(id) > 0) {
            return Result.failure().message("该处罚已经被员工关联，请先删除员工！");
        }
        return rewAndPuniService.deletePunishmentById(id) ? Result.success().message("删除成功！") : Result.failure().message("删除失败！");
    }

    @DeleteMapping("/removeEmployeeReward/{employeeId}")
    public synchronized Result removeEmployeeReward(@PathVariable("employeeId") Integer employeeId, @RequestBody Reward reward) {
        return rewAndPuniService.removeEmployeeReward(employeeId, reward) ? Result.success() : Result.failure();
    }

    @DeleteMapping("/removeEmployeePunishment/{employeeId}")
    public synchronized Result removeEmployeePunishment(@PathVariable("employeeId") Integer employeeId, @RequestBody Punishment punishment) {
        return rewAndPuniService.removeEmployeePunishment(employeeId, punishment)  ? Result.success() : Result.failure();
    }
}
