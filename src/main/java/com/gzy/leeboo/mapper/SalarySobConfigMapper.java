package com.gzy.leeboo.mapper;

import com.gzy.leeboo.dto.BasicSalarySob;
import com.gzy.leeboo.dto.EmployeeSalarySob;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalarySobConfigMapper {
    List<EmployeeSalarySob> getAllEmployeeSalarySobByName(String name);

    List<BasicSalarySob> getAllBasicSalarySob();

    Boolean updateEmployeeSalarySob(Integer salarySobId, Integer employeeId);
}
