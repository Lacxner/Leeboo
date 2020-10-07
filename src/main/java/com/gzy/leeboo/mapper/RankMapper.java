package com.gzy.leeboo.mapper;

import com.gzy.leeboo.entity.Rank;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RankMapper {
    List<Rank> getAllRanks();

    Boolean deleteRankById(Integer id);

    Boolean deleteBatchRankByIds(List<Integer> ids);

    Boolean addRank(Rank rank);

    Boolean updateRank(Rank rank);

    Boolean updateRankEnabled(Rank rank);
}
