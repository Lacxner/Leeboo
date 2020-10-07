package com.gzy.leeboo.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gzy.leeboo.utils.Result;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <h1>自定义的注销成功处理器</h1>
 *  <code>onLogoutSuccess</code> 方法会在 {@link LogoutFilter} 过滤器中被调用，
 * 具体的注销操作由 {@link LogoutHandler} 接口实现类的 <code>logout</code> 方法来实现，这里使用默认实现。
 * <br/> <code>onLogoutSuccess</code> 方法的主要作用是注销成功后返回对应的成功信息，具体的页面跳转交由前端来处理。
 */
@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            String jsonResult = new ObjectMapper().writeValueAsString(Result.success().message("退出成功！"));
            writer.write(jsonResult);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(writer != null) writer.close();
        }
    }
}
