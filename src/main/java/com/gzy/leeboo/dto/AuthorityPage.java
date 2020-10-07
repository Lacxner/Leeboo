package com.gzy.leeboo.dto;

import com.gzy.leeboo.entity.Role;

import java.io.Serializable;
import java.util.List;

/**
 * 用于前端 权限管理 页面的数据传输
 */
public class AuthorityPage implements Serializable {
    private static final long serialVersionUID = 2566930138879347896L;

    private Role role;
    private List<Integer> menuIds;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Integer> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<Integer> menuIds) {
        this.menuIds = menuIds;
    }
}
