package com.gzy.leeboo.mapper;

import com.gzy.leeboo.entity.Statistics;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticsMapper {
    Integer getEmployeeCounts();

    Integer getNewEmployeeCounts(Integer year);

    Integer getDepartmentCounts();

    Double getAllEmployeesAvgSalary();

    Boolean updateStatistics(Statistics statistics);
}
