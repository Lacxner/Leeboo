package com.gzy.leeboo.controller;

import com.gzy.leeboo.service.NoticeService;
import com.gzy.leeboo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/welcome/notice")
public class NoticeController {
    private NoticeService noticeService;

    @Autowired
    public void setNoticeService(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("/getNotice")
    public Result getNotice() {
        return Result.success().data("item", noticeService.getNotice());
    }
}
