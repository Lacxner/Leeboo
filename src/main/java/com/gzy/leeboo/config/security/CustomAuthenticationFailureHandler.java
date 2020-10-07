package com.gzy.leeboo.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gzy.leeboo.utils.Result;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <h1>自定义的认证失败处理器</h1>
 *  <code>onAuthenticationFailure</code> 方法会在 {@link AbstractAuthenticationProcessingFilter} 过滤器的
 * <code>unsuccessfulAuthentication</code> 方法中被调用，用于处理认证时抛出所有的 {@link InternalAuthenticationServiceException} 和
 * {@link AuthenticationException} 异常。
 * <br/> <code>onAuthenticationFailure</code> 方法是登录失败时的回调方法，主要作用是登录成功后返回对应的状态信息以及登录用户信息，
 * 登录失败的状态码均使用默认值400，前端可以获取失败状态信息并在页面中显示相应的错误信息。
 */
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            String jsonResult;
            if(exception instanceof BadCredentialsException){
                jsonResult = new ObjectMapper().writeValueAsString(Result.failure().message("用户名或密码错误！"));
            }else if(exception instanceof LockedException){
                jsonResult = new ObjectMapper().writeValueAsString(Result.failure().message("账户已被锁定！"));
            }else if(exception instanceof DisabledException){
                jsonResult = new ObjectMapper().writeValueAsString(Result.failure().message("账户已被禁用！"));
            }else{
                jsonResult = new ObjectMapper().writeValueAsString(Result.failure().message("登录失败，未知错误！"));
            }
            writer.write(jsonResult);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(writer != null) writer.close();
        }
    }
}
