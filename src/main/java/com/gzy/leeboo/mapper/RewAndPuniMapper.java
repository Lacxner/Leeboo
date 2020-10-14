package com.gzy.leeboo.mapper;

import com.gzy.leeboo.entity.Punishment;
import com.gzy.leeboo.entity.Reward;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RewAndPuniMapper {
    List<Reward> getAllRewards();
    
    List<Punishment> getAllPunishments();

    Reward getRewardById(Integer id);

    Punishment getPunishmentById(Integer id);

    List<Reward> getRewardsByEmployeeName(String name);

    List<Punishment> getPunishmentsByEmployeeName(String name);

    Boolean addReward(Reward reward);

    Boolean addPunishment(Punishment punishment);

    Boolean addEmployeeReward(Integer employeeId, Integer rewardId);

    Boolean addEmployeePunishment(Integer employeeId, Integer punishmentId);

    Boolean deleteRewardById(Integer id);

    Boolean deletePunishmentById(Integer id);

    Boolean deleteEmployeeReward(Integer employeeId, Integer rewardId);

    Boolean deleteEmployeePunishment(Integer employeeId, Integer punishmentId);
}
