package com.gzy.leeboo.config.security;

import com.gzy.leeboo.config.security.SMSCode.SMSCodeAuthenticationConfigurer;
import com.gzy.leeboo.config.security.SMSCode.SMSCodeAuthenticationFilter;
import com.gzy.leeboo.config.security.SMSCode.SMSCodeAuthenticationProvider;
import com.gzy.leeboo.service.HrService;
import com.gzy.leeboo.service.SMSCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity // 用于开启Spring Security的Web安全
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private HrService hrService;
    private SMSCodeService smsCodeService;
    private SMSCodeAuthenticationConfigurer smsCodeAuthenticationConfigurer;
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private CustomLogoutSuccessHandler customLogoutSuccessHandler;
    private CustomAccessDecisionManager customAccessDecisionManager;
    private CustomFilterInvocationSecurityMetadataSource customFilterInvocationSecurityMetadataSource;
    private CustomAccessDeniedHandler customAccessDeniedHandler;
    private CustomInvalidSessionStrategy customInvalidSessionStrategy;

    @Autowired
    public void setHrService(HrService hrService) {
        this.hrService = hrService;
    }

    @Autowired
    public void setSmsCodeService(SMSCodeService smsCodeService) {
        this.smsCodeService = smsCodeService;
    }

    @Autowired
    public void setSmsCodeAuthenticationConfigurer(SMSCodeAuthenticationConfigurer smsCodeAuthenticationConfigurer) {
        this.smsCodeAuthenticationConfigurer = smsCodeAuthenticationConfigurer;
    }

    @Autowired
    public void setMyAuthenticationSuccessHandler(CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler) {
        this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
    }

    @Autowired
    public void setMyAuthenticationFailureHandler(CustomAuthenticationFailureHandler customAuthenticationFailureHandler) {
        this.customAuthenticationFailureHandler = customAuthenticationFailureHandler;
    }

    @Autowired
    public void setCustomAuthenticationEntryPoint(CustomAuthenticationEntryPoint customAuthenticationEntryPoint) {
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
    }

    @Autowired
    public void setCustomLogoutSuccessHandler(CustomLogoutSuccessHandler customLogoutSuccessHandler) {
        this.customLogoutSuccessHandler = customLogoutSuccessHandler;
    }

    @Autowired
    public void setCustomAccessDecisionManager(CustomAccessDecisionManager customAccessDecisionManager) {
        this.customAccessDecisionManager = customAccessDecisionManager;
    }

    @Autowired
    public void setCustomFilterInvocationSecurityMetadataSource(CustomFilterInvocationSecurityMetadataSource customFilterInvocationSecurityMetadataSource) {
        this.customFilterInvocationSecurityMetadataSource = customFilterInvocationSecurityMetadataSource;
    }

    @Autowired
    public void setCustomAccessDeniedHandler(CustomAccessDeniedHandler customAccessDeniedHandler) {
        this.customAccessDeniedHandler = customAccessDeniedHandler;
    }

    @Autowired
    public void setCustomInvalidSessionStrategy(CustomInvalidSessionStrategy customInvalidSessionStrategy) {
        this.customInvalidSessionStrategy = customInvalidSessionStrategy;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 使用BCry算法进行密码盐加密
        auth.userDetailsService(hrService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/fonts/**", "/favicon.ico", "/index.html", "/druid/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 配置异常处理接口
        http.exceptionHandling()
                .authenticationEntryPoint(customAuthenticationEntryPoint)
                .accessDeniedHandler(customAccessDeniedHandler);

        // 配置短信验证码登录
        http.apply(smsCodeAuthenticationConfigurer);

        // 配置认证和授权规则
        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setAccessDecisionManager(customAccessDecisionManager);
                        object.setSecurityMetadataSource(customFilterInvocationSecurityMetadataSource);
                        return object;
                    }
                })
                .antMatchers("/login/**")
                .permitAll()
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated() // 表示所有请求都要认证和授权
                .and()
                .formLogin()
                .loginProcessingUrl("/login") // 登录处理url
                .successHandler(customAuthenticationSuccessHandler) // 设置自定义认证成功处理器
                .failureHandler(customAuthenticationFailureHandler) // 设置自定义认证失败处理器
                .permitAll();

        // 配置记住我
        http.rememberMe()
                .authenticationSuccessHandler(customAuthenticationSuccessHandler);

        // 配置Session
        http.sessionManagement()
                .invalidSessionStrategy(customInvalidSessionStrategy); // Session失效策略

        // 配置注销处理规则
        http.logout()
                .logoutUrl("/logout") // 注销处理url
                .deleteCookies("JSESSIONID") // 注销后清除浏览器的登录Cookie
                .logoutSuccessHandler(customLogoutSuccessHandler) // 设置自定义注销成功处理器
                .permitAll();

        // 关闭Csrf
        http.csrf().disable();
    }
}
