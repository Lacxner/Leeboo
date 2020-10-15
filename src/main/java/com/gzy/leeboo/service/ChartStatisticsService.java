package com.gzy.leeboo.service;

import com.gzy.leeboo.dto.DepartmentEmployeeChart;
import com.gzy.leeboo.mapper.ChartStatisticsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChartStatisticsService {
    private ChartStatisticsMapper chartStatisticsMapper;

    @Autowired
    public void setChartStatisticsMapper(ChartStatisticsMapper chartStatisticsMapper) {
        this.chartStatisticsMapper = chartStatisticsMapper;
    }

    public List<String> getAllDepartmentNames() {
        return chartStatisticsMapper.getAllDepartmentNames();
    }

    public List<DepartmentEmployeeChart> getAllDepartmentEmployeeCharts() {
        return chartStatisticsMapper.getAllDepartmentEmployeeCharts();
    }

    public List<Double> getDepartmentMinSalary() {
        return chartStatisticsMapper.getDepartmentMinSalary();
    }

    public List<Double> getDepartmentMaxSalary() {
        return chartStatisticsMapper.getDepartmentMaxSalary();
    }

    public List<Double> getDepartmentAvgSalary() {
        return chartStatisticsMapper.getDepartmentAvgSalary();
    }
}
