package com.gzy.leeboo.mapper;

import com.gzy.leeboo.dto.EmployeeMove;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeMoveMapper {
    EmployeeMove getEmployeeMoveByName(String name);

    Boolean moveEmployee(EmployeeMove employeeMove);
}
