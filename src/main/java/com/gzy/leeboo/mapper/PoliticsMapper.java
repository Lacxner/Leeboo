package com.gzy.leeboo.mapper;

import com.gzy.leeboo.entity.Politics;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PoliticsMapper {
    List<Politics> getAllPolitics();
}
