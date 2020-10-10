package com.gzy.leeboo.controller;

import com.gzy.leeboo.service.DatabaseService;
import com.gzy.leeboo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system/database")
public class DatabaseController {
    private DatabaseService databaseService;

    @Autowired
    public void setDatabaseService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @GetMapping("/getDatabase")
    public Result getDatabase() {
        return Result.success().data("item", databaseService.getDatabase());
    }

    @PostMapping("/backupDatabase")
    public Result backupDatabase() {
        return databaseService.backupDatabase() ? Result.success().message("备份成功！") : Result.failure().message("备份失败！");
    }

    @PostMapping("/restoreDatabase")
    public Result restoreDatabase() {
        return databaseService.restoreDatabase() ? Result.success().message("还原成功！") : Result.failure().message("还原失败！");
    }
}
