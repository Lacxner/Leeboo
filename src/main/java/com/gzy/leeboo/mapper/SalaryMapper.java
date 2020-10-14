package com.gzy.leeboo.mapper;

import com.gzy.leeboo.entity.SalarySob;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryMapper {
    Boolean addSalary(SalarySob salarySob);

    Boolean updateSalary(SalarySob salarySob);

    Boolean increaseSalaryReward(Integer money, Integer id);

    Boolean decreaseSalaryReward(Integer money, Integer id);

    Boolean increaseSalaryPunishment(Integer money, Integer id);

    Boolean decreaseSalaryPunishment(Integer money, Integer id);

    Boolean updateEmployeeSalary(Integer salaryId, Integer employeeId);

    Boolean computeActualSalary(Integer id);
}
