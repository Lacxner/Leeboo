package com.gzy.leeboo.controller;

import com.gzy.leeboo.entity.Hr;
import com.gzy.leeboo.entity.Menu;
import com.gzy.leeboo.service.MenuService;
import com.gzy.leeboo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MenuController {
    private MenuService menuService;

    @Autowired
    public void setMenuService(MenuService menuService){
        this.menuService = menuService;
    }

    @GetMapping("/getAllMenus")
    public Result getAllMenus(){
        // 获取当前上下文中认证的用户信息，为了安全使用服务器内部的相关用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            Hr hr = (Hr) authentication.getPrincipal();
            List<Menu> allMenu = menuService.getAllMenuByHrId(hr.getId());
            return Result.success().data("items", allMenu);
        }
        return Result.failure().code(401).message("您尚未登录！");
    }
}
