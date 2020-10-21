package com.gzy.leeboo.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * <h1>短信验证码登录的错误凭证异常</h1>
 */
public class BadSMSCodeCredentialsException extends AuthenticationException {
    public BadSMSCodeCredentialsException(String msg, Throwable t) {
        super(msg, t);
    }

    public BadSMSCodeCredentialsException(String msg) {
        super(msg);
    }
}
