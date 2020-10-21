package com.gzy.leeboo.config.security.SMSCode;

import com.gzy.leeboo.config.security.CustomAuthenticationFailureHandler;
import com.gzy.leeboo.config.security.CustomAuthenticationSuccessHandler;
import com.gzy.leeboo.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SMSCodeAuthenticationConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private HrService hrService;
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    private SMSCodeAuthenticationProvider smsCodeAuthenticationProvider;

    @Autowired
    public void setHrService(HrService hrService) {
        this.hrService = hrService;
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
    public void setSmsCodeAuthenticationProvider(SMSCodeAuthenticationProvider smsCodeAuthenticationProvider) {
        this.smsCodeAuthenticationProvider = smsCodeAuthenticationProvider;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 创建并配置SMSCodeAuthenticationFilter
        SMSCodeAuthenticationFilter smsCodeFilter = new SMSCodeAuthenticationFilter();
        AuthenticationManager sharedObject = http.getSharedObject(AuthenticationManager.class);
        smsCodeFilter.setAuthenticationManager(sharedObject);
        smsCodeFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
        smsCodeFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);
        // 创建并配置SMSCodeAuthenticationProvider
        smsCodeAuthenticationProvider.setHrService(hrService);
        // 添加短信验证码过滤器
        http.authenticationProvider(smsCodeAuthenticationProvider)
                .addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public SMSCodeAuthenticationProvider smsCodeAuthenticationProviderFactory() {
        return new SMSCodeAuthenticationProvider();
    }
}
