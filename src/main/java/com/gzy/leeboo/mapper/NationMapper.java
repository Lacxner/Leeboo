package com.gzy.leeboo.mapper;

import com.gzy.leeboo.entity.Nation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NationMapper {
    List<Nation> getAllNations();
}
