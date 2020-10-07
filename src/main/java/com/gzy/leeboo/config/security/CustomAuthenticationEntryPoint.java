package com.gzy.leeboo.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gzy.leeboo.utils.Result;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <h1>自定义的未认证异常处理入口</h1>
 *  <code>commence</code> 方法会在 {@link ExceptionTranslationFilter} 过滤器的 <code>sendStartAuthentication</code> 方法中被调用，
 * 用于处理所有的 {@link AuthenticationException} 和部分的 {@link AccessDeniedException} 异常。
 * <br/> <code>commence</code> 方法主要作用是告知前端当前并未认证，其定义了未认证时要返回的状态信息，未认证时返回的状态码是401，
 * 而在前后端分离中具体的登录页跳转交由前端处理。
 */
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            String jsonResult = new ObjectMapper().writeValueAsString(Result.failure().code(401).message("您尚未登录！"));
            writer.write(jsonResult);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) writer.close();
        }
    }
}
