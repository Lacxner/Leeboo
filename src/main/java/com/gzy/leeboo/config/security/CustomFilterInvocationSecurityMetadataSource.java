package com.gzy.leeboo.config.security;

import com.gzy.leeboo.entity.Menu;
import com.gzy.leeboo.entity.Role;
import com.gzy.leeboo.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * <h1>自定义的一个基于 FilterInvocation 的安全元数据资源管理器</h1>
 *  <code>getAttributes</code> 方法会在 {@link AbstractSecurityInterceptor} 抽象类中的 <code>beforeInvocation</code> 方法中被调用。
 * <br/> <code>getAttributes</code> 方法的主要作用就是当访问一个URL时返回这个URL所需要的访问权限，需要的访问权限存在一个 <code>ConfigAttribute</code> 接口的实现类
 * {@link SecurityConfig} 的集合中中。
 */
@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    private MenuService menuService;
    // Spring框架中的一个工具类，用于实现基于Ant风格的路径匹配功能
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // 获取当前请求的URL（非全URL）
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        // 获取所有菜单以及其对应的所需的所有角色
        List<Menu> menuList = menuService.getAllMenuWithRoles();

        /*
        * 用当前请求的URL与所有的菜单需要的URL进行匹配：
        *   - 如果匹配成功就表示当前请求的URL是在菜单页面可访问范围之内，然后将角色名称封装成SecurityConfig的集合（SecurityConfig是ConfigAttribute接口的实现类）
        *   - 如果匹配失败就表示请求的URL不是访问的菜单页面信息，就不需要返回ConfigAttribute集合（也可以返回一个空集合）
        */
        for (Menu menu : menuList) {
            // 判断当前请求的URL是否合法
            if (antPathMatcher.match(menu.getUrl(), requestUrl)) {
                // 获取匹配到的菜单其对应的所需的所有角色
                List<Role> roles = menu.getRoles();
                // 将所有的角色名称通过SecurityConfig工具类封装成一个SecurityConfig集合
                String[] roleNames = new String[roles.size()];
                for (int i = 0; i < roleNames.length; i++) {
                    roleNames[i] = roles.get(i).getName();
                }
                // 每个SecurityConfig中存储的是当前请求的URL通过鉴权需要的一个角色名称
                List<ConfigAttribute> configAttributes = SecurityConfig.createList(roleNames);
                return configAttributes;
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
