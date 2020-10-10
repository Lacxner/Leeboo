package com.gzy.leeboo.mapper;

import com.gzy.leeboo.dto.BasicSalary;
import com.gzy.leeboo.dto.EmployeeSalary;
import com.gzy.leeboo.entity.Salary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaryMapper {
    List<Salary> getAllSalary();

    List<EmployeeSalary> getAllEmployeeSalaryByName(String name);

    List<BasicSalary> getAllBasicSalary();

    Salary getSalaryByName(String name);

    Boolean addSalary(Salary salary);

    Boolean updateSalary(Salary salary);

    Boolean updateEmployeeSalary(Integer salaryId, Integer employeeId);

    Boolean addEmployeeSalary(Integer salaryId, Integer employeeId);

    Boolean deleteSalaryById(Integer id);

    Boolean deleteBatchSalaryByIds(List<Integer> ids);
}
