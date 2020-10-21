package com.gzy.leeboo.config.validator.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 用于校验手机号码
 */
@Documented
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Constraint(validatedBy = PhoneValidator.class)
@Retention(RUNTIME)
public @interface Phone {
    //手机号码的正则校验
    String regexp() default "^[1][3-9][0-9]{9}$";

    String message() default "手机号码格式不正确！";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
