package com.gzy.leeboo.service;

import com.gzy.leeboo.entity.Nation;
import com.gzy.leeboo.mapper.NationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@CacheConfig(cacheNames = "nation")
@Service
public class NationService {
    private NationMapper nationMapper;

    @Autowired
    public void setNationMapper(NationMapper nationMapper) {
        this.nationMapper = nationMapper;
    }

    @Cacheable(key = "'allNations'")
    public List<Nation> getAllNations() {
        return nationMapper.getAllNations();
    }
}
