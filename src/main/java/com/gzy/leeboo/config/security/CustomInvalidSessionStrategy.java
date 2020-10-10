package com.gzy.leeboo.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gzy.leeboo.utils.Result;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <h1>自定义的Session失效策略</h1>
 *  <code>onInvalidSessionDetected</code> 方法会在 {@link SessionManagementFilter} 过滤器中被调用，用于处理当前浏览器会话对应
 * Session 过期失效的情况。
 * <br/> <code>onInvalidSessionDetected</code> 方法主要作用是告知前端当前用户对应的 Session 已经失效，而 Sprig Security 的认证信息都是存在 Session 中的，
 * 其定义了失效时要返回的状态信息， Session 失效时返回的状态码是402（该状态码的用途是自己规定的），具体的用户注销操作由前端处理。
 */
@Component
public class CustomInvalidSessionStrategy implements InvalidSessionStrategy {
    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            String jsonResult = new ObjectMapper().writeValueAsString(Result.success().code(402).message("登录已过期，请重新登录！"));
            writer.write(jsonResult);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) writer.close();
        }

    }
}
