package com.gzy.leeboo.service;

import com.gzy.leeboo.entity.Salary;
import com.gzy.leeboo.mapper.SalarySearchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalarySearchService {
    private SalarySearchMapper salarySearchMapper;

    @Autowired
    public void setSalarySearchMapper(SalarySearchMapper salarySearchMapper) {
        this.salarySearchMapper = salarySearchMapper;
    }

    public Salary getSalaryByEmployeeName(String name) {
        return salarySearchMapper.getSalaryByEmployeeName(name);
    }
}
