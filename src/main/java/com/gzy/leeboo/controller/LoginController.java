package com.gzy.leeboo.controller;

import com.gzy.leeboo.config.validator.annotation.Phone;
import com.gzy.leeboo.service.SMSCodeService;
import com.gzy.leeboo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/login")
public class LoginController {
    private SMSCodeService smsCodeService;
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    public void setSmsCodeService(SMSCodeService smsCodeService) {
        this.smsCodeService = smsCodeService;
    }
    
    @Autowired
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @GetMapping("/sendShortMessage/{phone}")
    public Result sendShortMessage(@PathVariable("phone") @NotNull @Phone String phone) {
        return smsCodeService.sendShortMessage(phone) ? Result.success() : Result.failure().message("短信发送失败！");
    }
}
