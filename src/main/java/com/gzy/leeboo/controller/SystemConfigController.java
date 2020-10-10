package com.gzy.leeboo.controller;

import com.gzy.leeboo.entity.SystemConfig;
import com.gzy.leeboo.service.SystemConfigService;
import com.gzy.leeboo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system/config")
public class SystemConfigController {
    private SystemConfigService systemConfigService;

    @Autowired
    public void setSystemConfigService(SystemConfigService systemConfigService) {
        this.systemConfigService = systemConfigService;
    }

    @GetMapping("/getSystemConfig")
    public Result getSystemConfig() {
        return Result.success().data("item", systemConfigService.getSystemConfig());
    }

    @PutMapping("/updateSystemConfig")
    public Result updateSystemConfig(@RequestBody SystemConfig systemConfig) {
        return systemConfigService.updateSystemConfig(systemConfig) ? Result.success().message("修改成功！") : Result.failure().message("修改失败！");
    }
}
