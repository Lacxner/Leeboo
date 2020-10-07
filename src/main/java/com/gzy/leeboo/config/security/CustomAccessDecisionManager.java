package com.gzy.leeboo.config.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * <h1>自定义的授权管理器</h1>
 *  <code>decide</code> 方法会在 {@link AbstractSecurityInterceptor} 类的 <code>beforeInvocation</code> 方法中被调用，
 * 用于决定某个请求是否能通过授权，其中包括当前用户是否拥有需要的角色（即权限），或者该请求是否需要权限。
 * 如果用户鉴权通过，或者该请求无需权限，则不做任何处理，否则就抛出 {@link AccessDeniedException} 异常。该异常会被 {@link ExceptionTranslationFilter}
 * 过滤器拦截到并最终交给 {@link AccessDeniedHandler} 接口实现类处理。
 */
@Component
public class CustomAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        // 如果菜单页面需要权限，但是当前用户没有任何角色，则直接抛出异常
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        if (authorities == null || authorities.isEmpty()) {
            throw new AccessDeniedException("您权限不足！");
        }

        // 鉴权（只要有需要角色其中之一即可）
        for (ConfigAttribute configAttribute : configAttributes) {
            // 从SecurityConfig中获取需要的角色的名称
            String neededRole = configAttribute.getAttribute();
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals(neededRole)) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("您权限不足！");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
