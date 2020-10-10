package com.gzy.leeboo.controller;

import com.gzy.leeboo.dto.AuthenticateHr;
import com.gzy.leeboo.dto.BasicHr;
import com.gzy.leeboo.dto.ResetPassword;
import com.gzy.leeboo.dto.UpdateHrRole;
import com.gzy.leeboo.entity.Hr;
import com.gzy.leeboo.service.HrService;
import com.gzy.leeboo.service.RoleService;
import com.gzy.leeboo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.FileNotFoundException;

@RequestMapping("/system/hr")
@RestController
public class HrController {
    private HrService hrService;
    private RoleService roleService;

    @Autowired
    public void setHrService(HrService hrService) {
        this.hrService = hrService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/getMyself")
    public Result getMyself() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            Hr hr = (Hr) authentication.getPrincipal();
            return Result.success().data("item", hrService.getHrById(hr.getId()));
        }
        return Result.failure().code(401).message("尚未登录！");
    }

    @GetMapping("/getAllHrs")
    public Result getAllHrs() {
        return Result.success().data("items", hrService.getAllHrs());
    }

    @GetMapping("/getAllRoles")
    public Result getAllRoles() {
        return Result.success().data("items", roleService.getAllRoles());
    }

    @PostMapping("/addHr")
    public Result addHr(@RequestBody @Validated AuthenticateHr authenticateHr) {
        // 对密码进行盐加密
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        authenticateHr.setPassword(bCryptPasswordEncoder.encode(authenticateHr.getPassword()));
        return hrService.addHr(authenticateHr) ? Result.success().message("添加成功！") : Result.failure().message("添加失败！");
    }

    @PutMapping("/updateHrRole")
    public Result updateHrRole(@RequestBody UpdateHrRole updateHrRole) {
        return roleService.updateHrRole(updateHrRole) ? Result.success().message("修改成功！") : Result.failure().message("修改失败！");
    }

    @PutMapping("/updateHr")
    public Result updateHr(@RequestBody Hr hr) {
        return hrService.updateHr(hr) ? Result.success().message("修改成功！") : Result.failure().message("修改成功！");
    }

    @PutMapping("/updateHrPassword")
    public Result updateHrPassword(@RequestBody ResetPassword resetPassword) {
        // 对密码进行盐加密
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        resetPassword.setPassword(bCryptPasswordEncoder.encode(resetPassword.getPassword()));
        return hrService.updateHrPassword(resetPassword) ? Result.success().message("修改成功！") : Result.failure().message("修改失败！");
    }

    @PutMapping("/updateHrEnabled")
    public Result updateHrEnabled(@RequestBody BasicHr basicHr) {
        return hrService.updateHrEnabled(basicHr) ? Result.success() : Result.failure().message("修改失败！");
    }

    @PutMapping("/updateHrAvatar")
    public Result updateHrAvatar(@RequestBody BasicHr basicHr) {
        return hrService.updateHrAvatar(basicHr) ? Result.success().message("修改成功！") : Result.failure().message("修改失败！");
    }

    @DeleteMapping("/deleteHrById/{id}")
    public Result deleteHrById(@PathVariable("id") @NotNull @Min(1) Integer id) {
        return hrService.deleteHrById(id) ? Result.success().message("删除成功！") : Result.failure().message("删除失败！");
    }

    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file) throws FileNotFoundException {
        if ("".equals(file) || file.getSize() <= 0) {
            throw new FileNotFoundException();
        }
        return Result.success().data("item", hrService.upload(file));
    }
}
