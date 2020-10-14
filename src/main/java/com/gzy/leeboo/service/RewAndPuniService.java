package com.gzy.leeboo.service;

import com.gzy.leeboo.entity.Employee;
import com.gzy.leeboo.entity.Punishment;
import com.gzy.leeboo.entity.Reward;
import com.gzy.leeboo.mapper.EmployeeMapper;
import com.gzy.leeboo.mapper.RewAndPuniMapper;
import com.gzy.leeboo.mapper.SalaryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RewAndPuniService {
    private RewAndPuniMapper rewAndPuniMapper;
    private SalaryMapper salaryMapper;
    private EmployeeMapper employeeMapper;

    @Autowired
    public void setRewAndPuniMapper(RewAndPuniMapper rewAndPuniMapper) {
        this.rewAndPuniMapper = rewAndPuniMapper;
    }

    @Autowired
    public void setSalaryMapper(SalaryMapper salaryMapper) {
        this.salaryMapper = salaryMapper;
    }

    @Autowired
    public void setEmployeeMapper(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    public List<Reward> getAllRewards() {
        return rewAndPuniMapper.getAllRewards();
    }

    public List<Punishment> getAllPunishments() {
        return rewAndPuniMapper.getAllPunishments();
    }

    public List<Reward> getRewardsByEmployeeName(String name) {
        return rewAndPuniMapper.getRewardsByEmployeeName(name);
    }

    public List<Punishment> getPunishmentsByEmployeeName(String name) {
        return rewAndPuniMapper.getPunishmentsByEmployeeName(name);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public Boolean addReward(Reward reward) {
        return rewAndPuniMapper.addReward(reward);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public Boolean addPunishment(Punishment punishment) {
        return rewAndPuniMapper.addPunishment(punishment);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public Boolean addEmployeeReward(Integer employeeId, Integer rewardId) {
        Reward reward = rewAndPuniMapper.getRewardById(rewardId);
        Integer salaryId = employeeMapper.getSalaryIdById(employeeId);
        if (!rewAndPuniMapper.addEmployeeReward(employeeId, rewardId) || !salaryMapper.increaseSalaryReward(reward.getMoney(), salaryId)) {
            return false;
        }
        return true;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public Boolean addEmployeePunishment(Integer employeeId, Integer punishmentId) {
        Punishment punishment = rewAndPuniMapper.getPunishmentById(punishmentId);
        Integer salaryId = employeeMapper.getSalaryIdById(employeeId);
        if (!rewAndPuniMapper.addEmployeePunishment(employeeId, punishmentId) || !salaryMapper.increaseSalaryPunishment(punishment.getMoney(), salaryId)) {
            return false;
        }
        return true;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public Boolean removeEmployeeReward(Integer employeeId, Reward reward) {
        Integer salaryId = employeeMapper.getSalaryIdById(employeeId);
        if (!rewAndPuniMapper.deleteEmployeeReward(employeeId, reward.getId()) || !salaryMapper.decreaseSalaryReward(reward.getMoney(), salaryId)) {
            return false;
        }
        return true;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public Boolean removeEmployeePunishment(Integer employeeId, Punishment punishment) {
        Integer salaryId = employeeMapper.getSalaryIdById(employeeId);
        if (!rewAndPuniMapper.deleteEmployeePunishment(employeeId, punishment.getId()) || !salaryMapper.decreaseSalaryPunishment(punishment.getMoney(), salaryId)) {
            return false;
        }
        return true;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public Boolean deleteRewardById(Integer id) {
        return rewAndPuniMapper.deleteRewardById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public Boolean deletePunishmentById(Integer id) {
        return rewAndPuniMapper.deletePunishmentById(id);
    }
}
