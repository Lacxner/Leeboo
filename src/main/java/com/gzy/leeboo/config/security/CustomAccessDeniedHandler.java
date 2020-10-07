package com.gzy.leeboo.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gzy.leeboo.utils.Result;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <h1>自定义的授权拒绝处理器</h1>
 *  <code>handle</code> 方法会在 {@link ExceptionTranslationFilter} 过滤器的 <code>handleSpringSecurityException</code> 方法中被调用，
 * 用于处理部分 {@link AccessDeniedException} 异常（其余的会交由 {@link AuthenticationEntryPoint} 实现类来处理）。
 * <br/> <code>handle</code> 方法的主要作用是在授权拒绝时告知前端当前权限不足，其定义了授权拒绝时要返回的状态信息，授权拒绝时返回的状态码是403，
 * 而在前后端分离中具体的登录页跳转交由前端处理。
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            String jsonResult = new ObjectMapper().writeValueAsString(Result.success().code(403).message("您权限不足！"));
            writer.write(jsonResult);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) writer.close();
        }

    }
}
