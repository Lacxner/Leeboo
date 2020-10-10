package com.gzy.leeboo.mapper;

import com.gzy.leeboo.entity.Payday;
import org.springframework.stereotype.Repository;

@Repository
public interface PaydayMapper {
    Payday getPayday();

    Boolean updatePayday(Integer day);
}
