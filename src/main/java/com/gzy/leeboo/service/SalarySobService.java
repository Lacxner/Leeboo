package com.gzy.leeboo.service;

import com.gzy.leeboo.dto.BasicSalarySob;
import com.gzy.leeboo.dto.EmployeeSalarySob;
import com.gzy.leeboo.entity.SalarySob;
import com.gzy.leeboo.mapper.SalarySobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SalarySobService {
    private SalarySobMapper salarySobMapper;

    @Autowired
    public void setSalarySobMapper(SalarySobMapper salarySobMapper) {
        this.salarySobMapper = salarySobMapper;
    }

    public List<SalarySob> getAllSalarySob() {
        return salarySobMapper.getAllSalarySob();
    }

    public List<EmployeeSalarySob> getAllEmployeeSalarySobByName(String name) {
        return salarySobMapper.getAllEmployeeSalarySobByName(name);
    }

    public List<BasicSalarySob> getAllBasicSalarySob() {
        return salarySobMapper.getAllBasicSalarySob();
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public Boolean updateSalarySob(SalarySob salarySob) {
        return salarySobMapper.updateSalarySob(salarySob);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public Boolean updateEmployeeSalarySob(Integer salarySobId, Integer employeeId) {
        return salarySobMapper.updateEmployeeSalarySob(salarySobId, employeeId);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public Boolean addSalarySob(SalarySob salarySob) {
        return salarySobMapper.addSalarySob(salarySob);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public Boolean deleteSalarySobById(Integer id) {
        return salarySobMapper.deleteSalarySobById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public Boolean deleteBatchSalarySobByIds(List<Integer> ids) {
        return salarySobMapper.deleteBatchSalarySobByIds(ids);
    }
}
