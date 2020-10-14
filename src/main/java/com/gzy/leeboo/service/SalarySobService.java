package com.gzy.leeboo.service;

import com.gzy.leeboo.entity.SalarySob;
import com.gzy.leeboo.mapper.EmployeeMapper;
import com.gzy.leeboo.mapper.SalaryMapper;
import com.gzy.leeboo.mapper.SalarySobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public Boolean updateSalarySob(SalarySob salarySob) {
        return salarySobMapper.updateSalarySob(computeAllSalary(salarySob));
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public Boolean addSalarySob(SalarySob salarySob) {
        return salarySobMapper.addSalarySob(computeAllSalary(salarySob));
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public Boolean deleteSalarySobById(Integer id) {
        return salarySobMapper.deleteSalarySobById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public Boolean deleteBatchSalarySobByIds(List<Integer> ids) {
        return salarySobMapper.deleteBatchSalarySobByIds(ids);
    }

    /**
     * 计算工资账套的应发工资
     * @param salarySob 要计算的工资账套
     * @return 计算后的工资账套
     */
    private SalarySob computeAllSalary(SalarySob salarySob) {
        // 计算应发工资
        double allSalary = salarySob.getBasicSalary() + salarySob.getLunchSalary() + salarySob.getTrafficSalary()
                - (salarySob.getMedicalBase() * (salarySob.getMedicalPer() / 100))
                - (salarySob.getPensionBase() * (salarySob.getPensionPer() / 100))
                - (salarySob.getAccumulationFundBase() * (salarySob.getAccumulationFundPer() / 100));
        BigDecimal bigDecimal = new BigDecimal(allSalary);
        // 四舍五入精确到小数点后两位
        double scaleAllSalary = bigDecimal.setScale(2, RoundingMode.HALF_UP).doubleValue();
        salarySob.setAllSalary(scaleAllSalary);
        return salarySob;
    }
}
