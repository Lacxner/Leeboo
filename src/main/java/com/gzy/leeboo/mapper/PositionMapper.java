package com.gzy.leeboo.mapper;

import com.gzy.leeboo.entity.Position;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionMapper {
    List<Position> getAllPositions();

    Boolean deletePositionById(Integer id);

    Boolean deleteBatchPositionByIds(List<Integer> ids);

    Boolean addPosition(Position position);

    Boolean updatePositionEnabled(Position position);

    Boolean updatePositionName(Position position);
}
