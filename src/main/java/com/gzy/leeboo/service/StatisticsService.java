package com.gzy.leeboo.service;

import com.gzy.leeboo.entity.Statistics;
import com.gzy.leeboo.mapper.StatisticsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class StatisticsService {
    private StatisticsMapper statisticsMapper;

    @Autowired
    public void setStatisticsMapper(StatisticsMapper statisticsMapper) {
        this.statisticsMapper = statisticsMapper;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public Statistics getStatistics() {
        Integer empCounts = statisticsMapper.getEmployeeCounts();
        int year = LocalDate.now().getYear();
        Integer newEmpCounts = statisticsMapper.getNewEmployeeCounts(year);
        Integer depCounts = statisticsMapper.getDepartmentCounts();
        Double allEmpAvgSalary = statisticsMapper.getAllEmployeesAvgSalary();
        Statistics statistics = new Statistics(empCounts, newEmpCounts, depCounts, allEmpAvgSalary);
        statisticsMapper.updateStatistics(statistics);
        return statistics;
    }

    public Integer getEmployeeCounts() {
        return statisticsMapper.getEmployeeCounts();
    }

    public Integer getNewEmployeeCounts(Integer year) {
        return statisticsMapper.getNewEmployeeCounts(year);
    }

    public Integer getDepartmentCounts() {
        return statisticsMapper.getDepartmentCounts();
    }

    public Double getAllEmployeesAvgSalary() {
        return statisticsMapper.getAllEmployeesAvgSalary();
    }
}
