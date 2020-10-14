package com.gzy.leeboo.service;

import com.gzy.leeboo.entity.SalarySob;
import com.gzy.leeboo.mapper.SalaryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaryService {
    private SalaryMapper salaryMapper;

    @Autowired
    public void setSalaryService(SalaryMapper salaryMapper) {
        this.salaryMapper = salaryMapper;
    }

    public Boolean addSalary(SalarySob salarySob) {
        return salaryMapper.addSalary(salarySob);
    }

    public Boolean updateSalary(SalarySob salarySob) {
        return salaryMapper.updateSalary(salarySob);
    }
}
