package com.gzy.leeboo.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * <h1>短信验证码过期异常</h1>
 */
public class SMSCodeExpiredException extends AuthenticationException {
    public SMSCodeExpiredException(String msg, Throwable t) {
        super(msg, t);
    }

    public SMSCodeExpiredException(String msg) {
        super(msg);
    }
}
