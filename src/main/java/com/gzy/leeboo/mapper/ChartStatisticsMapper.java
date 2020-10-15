package com.gzy.leeboo.mapper;

import com.gzy.leeboo.dto.DepartmentEmployeeChart;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChartStatisticsMapper {
    List<String> getAllDepartmentNames();

    List<DepartmentEmployeeChart> getAllDepartmentEmployeeCharts();

    List<Double> getDepartmentMinSalary();

    List<Double> getDepartmentMaxSalary();

    List<Double> getDepartmentAvgSalary();
}
