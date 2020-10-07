package com.gzy.leeboo.mapper;

import com.gzy.leeboo.dto.SearchEmployee;
import com.gzy.leeboo.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeMapper {
    Employee getEmployeeById(Integer id);

    List<Employee> getAllEmployees();

    List<Employee> searchEmployees(SearchEmployee searchEmployee);

    Integer getMaxWorkID();

    Integer getEmployeeCountsByDepartmentId(Integer departmentId);

    Integer getEmployeeCountsByDepartmentIds(List<Integer> departmentIds);

    Integer getEmployeeCountsByPositionId(Integer positionId);

    Integer getEmployeeCountsByPositionIds(List<Integer> positionIds);

    Integer getEmployeeCountsByRankId(Integer rankId);

    Integer getEmployeeCountsByRankIds(List<Integer> rankIds);

    Integer getEmployeeCountsBySalaryId(Integer salaryId);

    Integer getEmployeeCountsBySalaryIds(List<Integer> salaryIds);

    Boolean addEmployee(Employee employee);

    Boolean addBatchEmployees(List<Employee> employees);

    Boolean updateEmployee(Employee employee);

    Boolean deleteEmployeeById(Integer id);

    Boolean deleteBatchEmployeeByIds(List<Integer> ids);
}
