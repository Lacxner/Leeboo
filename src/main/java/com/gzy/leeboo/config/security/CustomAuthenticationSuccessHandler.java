package com.gzy.leeboo.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gzy.leeboo.entity.Hr;
import com.gzy.leeboo.utils.Result;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <h1>自定义的认证成功处理器</h1>
 *  <code>onAuthenticationSuccess</code> 方法会在 {@link AbstractAuthenticationProcessingFilter} 过滤器的
 * <code>successfulAuthentication</code> 方法中被调用。
 * <br/> <code>onAuthenticationSuccess</code> 方法是登录成功时的回调方法，主要作用是登录成功后返回对应的状态信息以及登录用户信息，
 * 登录成功的状态码一律为默认值200，并且用户的密码会被做擦除保护。
 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // 设置响应体内容为JSON格式
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            // 从UsernamePasswordAuthenticationToken中获取Hr信息
            Hr hr = (Hr) authentication.getPrincipal();
            // 擦除密码
            hr.setPassword("[PROTECTED]");
            // 使用Jackson将Result类转换为JSON字符串形式
            String jsonResult = new ObjectMapper().writeValueAsString(Result.success().message("登录成功！").data("item", hr));
            writer.write(jsonResult);
        } catch (IOException e) {
            e.printStackTrace();
            if (writer != null) writer.close();
        }
    }
}
