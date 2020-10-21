package com.gzy.leeboo.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gzy.leeboo.dto.EmployeeSalarySob;
import com.gzy.leeboo.service.SalarySobConfigService;
import com.gzy.leeboo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@RequestMapping("/salary/sobConfig")
@RestController
public class SalarySobConfigController {
    private SalarySobConfigService salarySobConfigService;

    @Autowired
    public void setSalarySobService(SalarySobConfigService salarySobConfigService) {
        this.salarySobConfigService = salarySobConfigService;
    }

    @GetMapping("/getAllEmployeeSalarySobByName/{name}/{currentPage}/{pageSize}")
    public Result getAllEmployeeSalarySobByName(
            @PathVariable("name") @Pattern(regexp = "^[\\u4e00-\\u9fa5]{2,5}$", message = "员工姓名格式不正确！") String name,
            @PathVariable("currentPage") @NotNull @Min(1) Integer currentPage,
            @PathVariable("pageSize") @NotNull @Min(1) Integer pageSize) {
        if ("null".equals(name)) {
            name = null;
        }
        Page<EmployeeSalarySob> page = PageHelper.startPage(currentPage, pageSize, true, true, true);
        List<EmployeeSalarySob> allEmployeeSalary = salarySobConfigService.getAllEmployeeSalarySobByName(name);
        Long total = page.getTotal();
        return Result.success().data("items", allEmployeeSalary).data("total", total);
    }

    @GetMapping("/getAllBasicSalarySob")
    public Result getAllBasicSalarySob() {
        return Result.success().data("items", salarySobConfigService.getAllBasicSalarySob());
    }

    @PutMapping("/updateEmployeeSalarySob/{salarySobId}/{employeeId}")
    public Result updateEmployeeSalarySob(
            @PathVariable("salarySobId") @NotNull @Min(1) Integer salarySobId,
            @PathVariable("employeeId") @NotNull @Min(1) Integer employeeId) {
        return salarySobConfigService.updateEmployeeSalarySob(salarySobId, employeeId) ? Result.success().message("修改成功！") : Result.failure().message("修改失败！");
    }
}
