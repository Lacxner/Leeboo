package com.gzy.leeboo.mapper;

import com.gzy.leeboo.entity.Salary;
import com.gzy.leeboo.entity.SalarySob;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryMapper {
    Salary getSalaryByName(String name);

    Boolean addSalary(SalarySob salarySob);

    Boolean updateSalary(SalarySob salarySob);

    Boolean updateSalaryReward(Integer money, Integer id);

    Boolean updateSalaryPunishment(Integer money, Integer id);
}
