package com.gzy.leeboo.mapper;

import com.gzy.leeboo.dto.BasicSalarySob;
import com.gzy.leeboo.dto.EmployeeSalarySob;
import com.gzy.leeboo.entity.SalarySob;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalarySobMapper {
    List<SalarySob> getAllSalarySob();

    List<EmployeeSalarySob> getAllEmployeeSalarySobByName(String name);

    List<BasicSalarySob> getAllBasicSalarySob();

    Boolean addSalarySob(SalarySob salarySob);

    Boolean updateSalarySob(SalarySob salarySob);

    Boolean updateEmployeeSalarySob(Integer salarySobId, Integer employeeId);

    Boolean deleteSalarySobById(Integer id);

    Boolean deleteBatchSalarySobByIds(List<Integer> ids);
}
