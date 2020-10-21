package com.gzy.leeboo.config.security.SMSCode;

import com.gzy.leeboo.exception.BadSMSCodeCredentialsException;
import com.gzy.leeboo.exception.PhoneNotFoundException;
import com.gzy.leeboo.exception.SMSCodeExpiredException;
import com.gzy.leeboo.service.HrService;
import com.gzy.leeboo.service.SMSCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.util.Assert;

public class SMSCodeAuthenticationProvider implements AuthenticationProvider, InitializingBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(SMSCodeAuthenticationProvider.class);
    private UserDetailsChecker preAuthenticationChecks = new SMSCodeAuthenticationProvider.DefaultPreAuthenticationChecks();
    protected boolean hideUserNotFoundExceptions = true;
    private boolean forcePrincipalAsString = false;

    private HrService hrService;
    private SMSCodeService smsCodeService;
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    public void setHrService(HrService hrService) {
        this.hrService = hrService;
    }

    @Autowired
    public void setSmsCodeService(SMSCodeService smsCodeService) {
        this.smsCodeService = smsCodeService;
    }

    @Autowired
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(this.smsCodeService, "必须设置SMSCodeService");
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String phone = (authentication.getPrincipal() == null) ? "未提供" : authentication.getName();
        // 根据手机号码从数据库获取登录用户信息，如果获取的用户信息为空则抛出异常
        UserDetails user;
        try {
            user = retrieveUser(phone);
        } catch (PhoneNotFoundException notFound) {
            if (hideUserNotFoundExceptions) {
                throw new BadSMSCodeCredentialsException("短信验证码错误！");
            } else {
                throw notFound;
            }
        }

        // 认证前的预检测
        preAuthenticationChecks.check(user);
        // 真正的认证处理
        additionalAuthenticationChecks(user, (SMSCodeAuthenticationToken) authentication);

        Object principalToReturn = user;
        if (forcePrincipalAsString) {
            principalToReturn = user.getUsername();
        }
        return createSuccessAuthentication(principalToReturn, authentication, user);
    }

    /**
     * 创建登录成功的认证令牌
     * @param principal 要存入令牌的认证信息
     * @param authentication 原始的认证令牌
     * @param user 从数据库获取的用户信息
     * @return 登录成功的认证令牌
     */
    protected Authentication createSuccessAuthentication(Object principal, Authentication authentication, UserDetails user) {
        SMSCodeAuthenticationToken result = new SMSCodeAuthenticationToken(principal, authentication.getCredentials(), user.getAuthorities());
        result.setDetails(authentication.getDetails());
        return result;
    }

    /**
     * 验证码校验过程
     * @param userDetails 从数据库获取的登录用户信息
     * @param authentication 短信验证码认证令牌
     * @throws AuthenticationException
     */
    protected void additionalAuthenticationChecks(UserDetails userDetails, SMSCodeAuthenticationToken authentication) throws AuthenticationException {
        String phone = (String) authentication.getPrincipal();
        // 正确的短信验证码
        String correctSMSCode = stringRedisTemplate.opsForValue().get(phone);
        // 用户所提交的短信验证码
        String presentedSMSCode = authentication.getCredentials().toString();

        if (correctSMSCode == null) {
            throw new SMSCodeExpiredException("短信验证码已过期！");
        }
        if (presentedSMSCode == null) {
            throw new BadSMSCodeCredentialsException("短信验证码错误！");
        }
        if (!presentedSMSCode.equals(correctSMSCode)) {
            throw new BadSMSCodeCredentialsException("短信验证码错误！");
        }
    }

    /**
     * 通过手机号码检索用户
     * @param phone 手机号码
     * @return 登录用户
     */
    protected final UserDetails retrieveUser(String phone) throws AuthenticationException {
        try {
            // 检索用户，如果未找到用户会抛出 BadSMSCodeCredentialsException 异常
            UserDetails loadedUser = hrService.loadUserByPhone(phone);
            return loadedUser;
        } catch (PhoneNotFoundException ex) {
            throw ex;
        } catch (InternalAuthenticationServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex);
        }
    }

    /**
     * 用于判断当前 AuthenticationProvider 是否支持去认证该 Authenticaiton
     * @param authentication 被检验的 Authentication
     * @return 是否支持去认证
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return (SMSCodeAuthenticationToken.class.isAssignableFrom(authentication));
    }

    private class DefaultPreAuthenticationChecks implements UserDetailsChecker {
        /**
         * 认证前对用进行预检查
         * @param user 当前正在登录的用户
         */
        public void check(UserDetails user) {
            // 判断账户是否被锁定
            if (!user.isAccountNonLocked()) {
                LOGGER.debug("账户已被锁定！");
                throw new LockedException("账户已被锁定！");
            }

            // 判断账户是否可用
            if (!user.isEnabled()) {
                LOGGER.debug("账户已被禁用！");
                throw new DisabledException("账户已被禁用！");
            }

            // 判断账户是否已过期
            if (!user.isAccountNonExpired()) {
                LOGGER.debug("账户已过期！");
                throw new AccountExpiredException("账户已过期！");
            }
        }
    }

    public boolean isHideUserNotFoundExceptions() {
        return hideUserNotFoundExceptions;
    }

    /**
     * 设置是否隐藏用户不存在异常 {@link PhoneNotFoundException}，默认是将其隐藏，只抛出错误凭证异常，提高系统的安全性
     * @param hideUserNotFoundExceptions 是否隐藏用户不存在异常
     */
    public void setHideUserNotFoundExceptions(boolean hideUserNotFoundExceptions) {
        this.hideUserNotFoundExceptions = hideUserNotFoundExceptions;
    }
}
