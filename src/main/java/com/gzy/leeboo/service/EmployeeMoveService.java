package com.gzy.leeboo.service;

import com.gzy.leeboo.dto.EmployeeMove;
import com.gzy.leeboo.mapper.EmployeeMoveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeMoveService {
    private EmployeeMoveMapper employeeMoveMapper;

    @Autowired
    public void setEmployeeMoveMapper(EmployeeMoveMapper employeeMoveMapper) {
        this.employeeMoveMapper = employeeMoveMapper;
    }

    public EmployeeMove getEmployeeMoveByName(String name) {
        return employeeMoveMapper.getEmployeeMoveByName(name);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public Boolean moveEmployee(EmployeeMove employeeMove) {
        return employeeMoveMapper.moveEmployee(employeeMove);
    }
}
