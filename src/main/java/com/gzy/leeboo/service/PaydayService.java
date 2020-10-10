package com.gzy.leeboo.service;

import com.gzy.leeboo.entity.Payday;
import com.gzy.leeboo.mapper.PaydayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaydayService {
    private PaydayMapper paydayMapper;

    @Autowired
    public void setPaydayMapper(PaydayMapper paydayMapper) {
        this.paydayMapper = paydayMapper;
    }

    public Payday getPayday() {
        return paydayMapper.getPayday();
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public Boolean updatePayday(Integer day) {
        return paydayMapper.updatePayday(day);
    }
}
