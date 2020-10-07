package com.gzy.leeboo.service;

import com.gzy.leeboo.entity.Politics;
import com.gzy.leeboo.mapper.PoliticsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@CacheConfig(cacheNames = "politics")
@Service
public class PoliticsService {
    private PoliticsMapper politicsMapper;

    @Autowired
    public void setPoliticsMapper(PoliticsMapper politicsMapper) {
        this.politicsMapper = politicsMapper;
    }

    @Cacheable(key = "'allPolitics'")
    public List<Politics> getAllPolitics() {
        return politicsMapper.getAllPolitics();
    }
}
