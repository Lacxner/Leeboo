package com.gzy.leeboo.mapper;

import com.gzy.leeboo.entity.Salary;
import org.springframework.stereotype.Repository;

@Repository
public interface SalarySearchMapper {
    Salary getSalaryByEmployeeName(String name);
}
