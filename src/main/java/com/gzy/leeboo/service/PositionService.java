package com.gzy.leeboo.service;

import com.gzy.leeboo.entity.Position;
import com.gzy.leeboo.mapper.PositionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@CacheConfig(cacheNames = "position")
@Service
public class PositionService {
    private PositionMapper positionMapper;

    @Autowired
    public void setPositionMapper(PositionMapper positionMapper) {
        this.positionMapper = positionMapper;
    }

    @Cacheable(key = "'allPositions'")
    public List<Position> getAllPositions() {
        return positionMapper.getAllPositions();
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    @CacheEvict(key = "'allPositions'")
    public Boolean deletePositionById(Integer id) {
        return positionMapper.deletePositionById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    @CacheEvict(key = "'allPositions'")
    public Boolean deleteBatchPositionByIds(List<Integer> ids) {
        return positionMapper.deleteBatchPositionByIds(ids);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    @CacheEvict(key = "'allPositions'")
    public Boolean addPosition(Position position) {
        return positionMapper.addPosition(position);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    @CacheEvict(key = "'allPositions'")
    public Boolean updatePositionEnabled(Position position) {
        return positionMapper.updatePositionEnabled(position);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    @CacheEvict(key = "'allPositions'")
    public Boolean updatePositionName(Position position) {
        return positionMapper.updatePositionName(position);
    }
}
