package com.gzy.leeboo.controller;

import com.gzy.leeboo.entity.Notice;
import com.gzy.leeboo.entity.SystemConfig;
import com.gzy.leeboo.service.NoticeService;
import com.gzy.leeboo.service.SystemConfigService;
import com.gzy.leeboo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system/config")
public class SystemConfigController {
    private SystemConfigService systemConfigService;
    private NoticeService noticeService;

    @Autowired
    public void setSystemConfigService(SystemConfigService systemConfigService) {
        this.systemConfigService = systemConfigService;
    }

    @Autowired
    public void setNoticeService(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("/getSystemConfig")
    public Result getSystemConfig() {
        return Result.success()
                .data("systemConfig", systemConfigService.getSystemConfig())
                .data("notice", noticeService.getNotice());
    }

    @PutMapping("/updateSystemConfig")
    public Result updateSystemConfig(@RequestBody SystemConfig systemConfig) {
        return systemConfigService.updateSystemConfig(systemConfig) ? Result.success().message("修改成功！") : Result.failure().message("修改失败！");
    }
}
