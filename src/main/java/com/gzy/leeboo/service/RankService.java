package com.gzy.leeboo.service;

import com.gzy.leeboo.entity.Rank;
import com.gzy.leeboo.mapper.RankMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@CacheConfig(cacheNames = "rank")
@Service
public class RankService {
    private RankMapper rankMapper;

    @Autowired
    public void setRankMapper(RankMapper rankMapper) {
        this.rankMapper = rankMapper;
    }

    @Cacheable(key = "'allRanks'")
    public List<Rank> getAllRanks() {
        return rankMapper.getAllRanks();
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    @CacheEvict(key = "'allRanks'")
    public Boolean deleteRankById(Integer id) {
        return rankMapper.deleteRankById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    @CacheEvict(key = "'allRanks'")
    public Boolean deleteBatchRankByIds(List<Integer> ids) {
        return rankMapper.deleteBatchRankByIds(ids);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    @CacheEvict(key = "'allRanks'")
    public Boolean addRank(Rank rank) {
        return rankMapper.addRank(rank);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    @CacheEvict(key = "'allRanks'")
    public Boolean updateRank(Rank rank) {
        return rankMapper.updateRank(rank);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    @CacheEvict(key = "'allRanks'")
    public Boolean updateRankEnabled(Rank rank) {
        return rankMapper.updateRankEnabled(rank);
    }
}
