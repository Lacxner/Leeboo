package com.gzy.leeboo.service;

import com.gzy.leeboo.entity.Punishment;
import com.gzy.leeboo.entity.Reward;
import com.gzy.leeboo.mapper.RewAndPuniMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RewAndPuniService {
    private RewAndPuniMapper rewAndPuniMapper;

    @Autowired
    public void setRewAndPuniMapper(RewAndPuniMapper rewAndPuniMapper) {
        this.rewAndPuniMapper = rewAndPuniMapper;
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
        return rewAndPuniMapper.addEmployeeReward(employeeId, rewardId);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public Boolean addEmployeePunishment(Integer employeeId, Integer punishmentId) {
        return rewAndPuniMapper.addEmployeePunishment(employeeId, punishmentId);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public Boolean deleteRewardById(Integer id) {
        return rewAndPuniMapper.deleteRewardById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public Boolean deletePunishmentById(Integer id) {
        return rewAndPuniMapper.deletePunishmentById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public Boolean deleteEmployeeReward(Integer employeeId, Integer rewardId) {
        return rewAndPuniMapper.deleteEmployeeReward(employeeId, rewardId);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public Boolean deleteEmployeePunishment(Integer employeeId, Integer punishmentId) {
        return rewAndPuniMapper.deleteEmployeePunishment(employeeId, punishmentId);
    }
}
