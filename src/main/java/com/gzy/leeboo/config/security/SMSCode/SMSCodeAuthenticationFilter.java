package com.gzy.leeboo.config.security.SMSCode;

import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <h1>短信验证码认证过滤器</h1>
 */
public class SMSCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    // 手机号码的参数名称
    public static final String SPRING_SECURITY_FORM_PHONE_KEY = "phone";
    public static final String SPRING_SECURITY_FORM_SMSCODE_KEY = "smsCode";

    private String phoneParameter = SPRING_SECURITY_FORM_PHONE_KEY;
    private String smsCodeParameter = SPRING_SECURITY_FORM_SMSCODE_KEY;
    private boolean postOnly = true;

    /**
     * 设置短信验证码登录所拦截的URL和Http方法
     */
    public SMSCodeAuthenticationFilter() {
        super(new AntPathRequestMatcher("/smsCodeLogin", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        // 判断是否只接受POST请求
        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("认证方法不支持: " + request.getMethod());
        }
        // 从Http请求中获取手机号码
        String phone = obtainPhone(request);
        String smsCode = obtainSMSCode(request);

        // 数据校验
        if (phone == null) {
            phone = "";
        }
        if (smsCode == null) {
            smsCode = "";
        }
        phone = phone.trim();

        // 构建短信验证码认证令牌
        SMSCodeAuthenticationToken authRequest = new SMSCodeAuthenticationToken(phone, smsCode);
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    /**
     * 从请求中获取手机号码
     * @param request Http请求
     * @return 手机号码
     */
    @Nullable
    protected String obtainPhone(HttpServletRequest request) {
        return request.getParameter(phoneParameter);
    }

    /**
     * 从请求中获取短信验证码
     * @param request Http请求
     * @return 短信验证码
     */
    @Nullable
    protected String obtainSMSCode(HttpServletRequest request) {
        return request.getParameter(smsCodeParameter);
    }

    /**
     * 设置认证请求的细节参数
     * @param request Http请求
     * @param authRequest 短信验证码认证令牌
     */
    protected void setDetails(HttpServletRequest request, SMSCodeAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    /**
     * 设置是否只接收Http的POST请求，默认是只允许POST请求
     * @param postOnly 是否只允许POST请求表示标识
     */
    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    /**
     * 设置手机号码参数的名称
     * @param phoneParameter 手机号码参数的名称
     */
    public void setPhoneParameter(String phoneParameter) {
        Assert.hasText(phoneParameter, "手机号码参数的名称不能为空或者Null");
        this.phoneParameter = phoneParameter;
    }


    /**
     * 获取手机号码参数的名称
     * @return 手机号码参数的名称
     */
    public final String getPhoneParameter() {
        return phoneParameter;
    }

    /**
     * 设置短信验证码参数的名称
     * @param smsCodeParameter 短信验证码参数的名称
     */
    public void setSMSCodeParameter(String smsCodeParameter) {
        Assert.hasText(smsCodeParameter, "短信验证码参数的名称不能为空或者Null");
        this.smsCodeParameter = smsCodeParameter;
    }

    /**
     * 获取短信验证码参数的名称
     * @return 短信验证码参数的名称
     */
    public final String getSMSCodeParameter() {
        return smsCodeParameter;
    }
}
