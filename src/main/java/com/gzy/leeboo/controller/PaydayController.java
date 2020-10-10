package com.gzy.leeboo.controller;

import com.gzy.leeboo.entity.Payday;
import com.gzy.leeboo.service.PaydayService;
import com.gzy.leeboo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/salary/payday")
public class PaydayController {
    private PaydayService paydayService;

    @Autowired
    public void setPaydayService(PaydayService paydayService) {
        this.paydayService = paydayService;
    }

    @GetMapping("/getPayday")
    public Result getPayday() {
        return Result.success().data("item", paydayService.getPayday());
    }

    @PutMapping("/updatePayday/{day}")
    public Result updatePayday(@PathVariable Integer day) {
        return paydayService.updatePayday(day) ? Result.success().message("修改成功！") : Result.failure().message("修改失败！");
    }
}
