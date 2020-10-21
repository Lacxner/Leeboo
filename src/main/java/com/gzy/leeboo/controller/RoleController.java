package com.gzy.leeboo.controller;

import com.gzy.leeboo.dto.AuthorityPage;
import com.gzy.leeboo.entity.Role;
import com.gzy.leeboo.service.HrService;
import com.gzy.leeboo.service.MenuService;
import com.gzy.leeboo.service.RoleService;
import com.gzy.leeboo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("/authority/role")
@RestController
public class RoleController {
    private RoleService roleService;
    private HrService hrService;
    private MenuService menuService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setHrService(HrService hrService) {
        this.hrService = hrService;
    }

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/getAllRoles")
    public Result getAllRoles() {
        return Result.success().data("items", roleService.getAllRoles());
    }

    @GetMapping("/getAllMenuIdsByRoleId/{roleId}")
    public Result getAllMenuIdsByRoleId(@PathVariable("roleId") @NotNull @Min(1) Integer roleId) {
        return Result.success().data("items", menuService.getAllMenuIdsByRoleId(roleId));
    }

    @GetMapping("/getAllBasicMenu")
    public Result getAllBasicMenu() {
        return Result.success().data("items", menuService.getAllBasicMenu());
    }

    @PostMapping("/addRoleWithMenu")
    public Result addRoleWithMenu(@RequestBody @Validated AuthorityPage authorityPage) {
        Role role = authorityPage.getRole();
        List<Integer> menuIds = authorityPage.getMenuIds();

        // 角色名称的合法性处理
        role.setName(role.getName().toUpperCase());
        if (!role.getName().startsWith("ROLE_")) {
            role.setName("ROLE_" + role.getName());
        }

        // 添加用户信息及其菜单
        if (!roleService.addRoleWithMenu(role, menuIds)) {
            return Result.failure().message("添加失败！");
        }
        return Result.success().message("添加成功！");
    }

    @PutMapping("/updateRoleWithMenu")
    public Result updateRoleWithMenu(@RequestBody @Validated AuthorityPage authorityPage) {
        Role role = authorityPage.getRole();
        List<Integer> menuIds = authorityPage.getMenuIds();

        // 角色名称的合法性处理
        role.setName(role.getName().toUpperCase());
        if (!role.getName().startsWith("ROLE_")) {
            role.setName("ROLE_" + role.getName());
        }
        return roleService.updateRoleWithMenu(role, menuIds) ? Result.success().message("修改成功！") : Result.failure().message("修改失败！");
    }

    @DeleteMapping("/deleteRoleById/{id}")
    public Result deleteRoleById(@PathVariable("id") @NotNull @Min(1) Integer id) {
        if (hrService.getHrCountsByRoleId(id) > 0) {
            return Result.failure().message("该角色已经被操作员关联，请先删除操作员！");
        }
        return roleService.deleteRoleById(id) ? Result.success().message("删除成功！") : Result.failure().message("删除失败！");
    }

    @DeleteMapping("/deleteBatchRoleByIds")
    public Result deleteBatchRoleByIds(@RequestBody @NotNull List<Integer> ids) {
        if (hrService.getHrCountsByRoleIds(ids) > 0) {
            return Result.failure().message("其中的某个角色已经被操作员关联，请先删除操作员！");
        }
        return roleService.deleteBatchRoleByIds(ids) ? Result.success().message("删除成功！") : Result.failure().message("删除失败！");
    }
}
