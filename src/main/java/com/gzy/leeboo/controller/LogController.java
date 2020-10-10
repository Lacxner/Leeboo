package com.gzy.leeboo.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gzy.leeboo.entity.Log;
import com.gzy.leeboo.service.LogService;
import com.gzy.leeboo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/log")
public class LogController {
    private LogService LogService;

    @Autowired
    public void setLogService(LogService LogService) {
        this.LogService = LogService;
    }

    @GetMapping("/getAllLogs/{username}/{currentPage}/{pageSize}")
    public Result getAllLogs(@PathVariable("username") String username, @PathVariable("currentPage") Integer currentPage, @PathVariable("pageSize") Integer pageSize) {
        if ("null".equals(username)) {
            username = null;
        }
        Page<Log> page = PageHelper.startPage(currentPage, pageSize, true, true, true);
        List<Log> allLogs = LogService.getAllLogs(username);
        Long total = page.getTotal();
        return Result.success().data("items", allLogs).data("total", total);
    }

    @PostMapping("/addLog")
    public Result addLog(Log Log) {
        return LogService.addLog(Log) ? Result.success().message("添加成功！") : Result.failure().message("添加失败！");
    }

    @DeleteMapping("/deleteAllLogs")
    public Result deleteAllLogs() {
        return LogService.deleteAllLogs() == 0 ? Result.success().message("删除成功！") : Result.failure().message("删除失败！");
    }
}
