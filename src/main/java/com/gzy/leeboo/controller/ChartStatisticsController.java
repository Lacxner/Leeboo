package com.gzy.leeboo.controller;

import com.gzy.leeboo.service.ChartStatisticsService;
import com.gzy.leeboo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics/chart")
public class ChartStatisticsController {
    private ChartStatisticsService chartStatisticsService;

    @Autowired
    public void setChartStatisticsService(ChartStatisticsService chartStatisticsService) {
        this.chartStatisticsService = chartStatisticsService;
    }

    @GetMapping("/getSalaryChartInfo")
    public Result getSalaryChartInfo() {
        return Result.success()
                .data("departmentNames", chartStatisticsService.getAllDepartmentNames())
                .data("minSalaryList", chartStatisticsService.getDepartmentMinSalary())
                .data("maxSalaryList", chartStatisticsService.getDepartmentMaxSalary())
                .data("avgSalaryList", chartStatisticsService.getDepartmentAvgSalary());
    }

    @GetMapping("/getEmployeeChartInfo")
    public Result getEmployeeChartInfo() {
        return Result.success()
                .data("items", chartStatisticsService.getAllDepartmentEmployeeCharts());
    }
}
