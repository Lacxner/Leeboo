package com.gzy.leeboo.service;

import com.gzy.leeboo.dto.BasicSalary;
import com.gzy.leeboo.dto.EmployeeSalary;
import com.gzy.leeboo.entity.Salary;
import com.gzy.leeboo.mapper.SalaryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@CacheConfig(cacheNames = "salary")
@Service
public class SalaryService {
    private SalaryMapper salaryMapper;

    @Autowired
    public void setSalaryMapper(SalaryMapper salaryMapper) {
        this.salaryMapper = salaryMapper;
    }

    @Cacheable(key = "'allSalary'")
    public List<Salary> getAllSalary() {
        return salaryMapper.getAllSalary();
    }

    public List<EmployeeSalary> getAllEmployeeSalary() {
        return salaryMapper.getAllEmployeeSalary();
    }

    public List<BasicSalary> getAllBasicSalary() {
        return salaryMapper.getAllBasicSalary();
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    @CacheEvict(key = "'allSalary'")
    public Boolean updateSalary(Salary salary) {
        return salaryMapper.updateSalary(salary);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public Boolean updateEmployeeSalary(Integer salaryId, Integer employeeId) {
        return salaryMapper.updateEmployeeSalary(salaryId, employeeId);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    @CacheEvict(key = "'allSalary'")
    public Boolean addSalary(Salary salary) {
        return salaryMapper.addSalary(salary);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    @CacheEvict(key = "'allSalary'")
    public Boolean deleteSalaryById(Integer id) {
        return salaryMapper.deleteSalaryById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    @CacheEvict(key = "'allSalary'")
    public Boolean deleteBatchSalaryByIds(List<Integer> ids) {
        return salaryMapper.deleteBatchSalaryByIds(ids);
    }
}
