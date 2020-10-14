package com.gzy.leeboo.service;

import com.gzy.leeboo.dto.BasicSalarySob;
import com.gzy.leeboo.dto.EmployeeSalarySob;
import com.gzy.leeboo.entity.SalarySob;
import com.gzy.leeboo.mapper.EmployeeMapper;
import com.gzy.leeboo.mapper.SalaryMapper;
import com.gzy.leeboo.mapper.SalarySobConfigMapper;
import com.gzy.leeboo.mapper.SalarySobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SalarySobConfigService {
    private SalarySobConfigMapper salarySobConfigMapper;
    private SalarySobMapper salarySobMapper;
    private EmployeeMapper employeeMapper;
    private SalaryMapper salaryMapper;

    @Autowired
    public void setSalarySobConfigMapper(SalarySobConfigMapper salarySobConfigMapper) {
        this.salarySobConfigMapper = salarySobConfigMapper;
    }

    @Autowired
    public void setSalarySobMapper(SalarySobMapper salarySobMapper) {
        this.salarySobMapper = salarySobMapper;
    }

    @Autowired
    public void setEmployeeMapper(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    @Autowired
    public void setSalaryMapper(SalaryMapper salaryMapper) {
        this.salaryMapper = salaryMapper;
    }

    public List<EmployeeSalarySob> getAllEmployeeSalarySobByName(String name) {
        return salarySobConfigMapper.getAllEmployeeSalarySobByName(name);
    }

    public List<BasicSalarySob> getAllBasicSalarySob() {
        return salarySobConfigMapper.getAllBasicSalarySob();
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public Boolean updateEmployeeSalarySob(Integer salarySobId, Integer employeeId) {
        // 为员工设置工资账套
        if (!salarySobConfigMapper.updateEmployeeSalarySob(salarySobId, employeeId)) {
            return false;
        }
        SalarySob salarySob = salarySobMapper.getSalarySobById(salarySobId);
        // 先获取员工的工资id，根据工资id是否为空来判断是添加还是修改
        Integer salaryId = employeeMapper.getSalaryIdById(employeeId);
        if (salaryId == null) {
            if (!salaryMapper.addSalary(salarySob)
                    || !salaryMapper.updateEmployeeSalary(salarySob.getId(), employeeId)
                    || !salaryMapper.computeActualSalary(salarySob.getId())) {
                return false;
            }
        } else {
            if (!salaryMapper.updateSalary(salarySob) || !salaryMapper.computeActualSalary(salaryId)) {
                return false;
            }
        }
        return true;
    }
}
