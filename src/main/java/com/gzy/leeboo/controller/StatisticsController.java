package com.gzy.leeboo.controller;

import com.gzy.leeboo.entity.Statistics;
import com.gzy.leeboo.service.StatisticsService;
import com.gzy.leeboo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/statistics/all")
public class StatisticsController {
    private StatisticsService statisticsService;

    @Autowired
    public void setStatisticsService(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/getStatistics")
    public Result getStatistics() {
        Statistics statistics = statisticsService.getStatistics();
        return Result.success().data("item", statistics);
    }
}
