package com.gzy.leeboo.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gzy.leeboo.entity.SystemLog;
import com.gzy.leeboo.service.SystemLogService;
import com.gzy.leeboo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/log")
public class SystemLogController {
    private SystemLogService systemLogService;

    @Autowired
    public void setSystemLogService(SystemLogService systemLogService) {
        this.systemLogService = systemLogService;
    }

    @GetMapping("/getAllSystemLogs/{username}/{currentPage}/{pageSize}")
    public Result getAllSystemLogs(@PathVariable("username") String username, @PathVariable("currentPage") Integer currentPage, @PathVariable("pageSize") Integer pageSize) {
        if ("null".equals(username)) {
            username = null;
        }
        Page<SystemLog> page = PageHelper.startPage(currentPage, pageSize, true, true, true);
        List<SystemLog> allSystemLogs = systemLogService.getAllSystemLogs(username);
        Long total = page.getTotal();
        return Result.success().data("items", allSystemLogs).data("total", total);
    }

    @PostMapping("/addSystemLog")
    public Result addSystemLog(SystemLog systemLog) {
        return systemLogService.addSystemLog(systemLog) ? Result.success().message("添加成功！") : Result.failure().message("添加失败！");
    }

    @DeleteMapping("/deleteAllSystemLogs")
    public Result deleteAllSystemLogs() {
        return systemLogService.deleteAllSystemLogs() == 0 ? Result.success().message("删除成功！") : Result.failure().message("删除失败！");
    }
}
