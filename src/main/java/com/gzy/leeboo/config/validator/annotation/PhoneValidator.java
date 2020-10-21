package com.gzy.leeboo.config.validator.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 手机号码校验器，用于 {@link Phone} 注解。
 */
public class PhoneValidator implements ConstraintValidator<Phone, String> {
    private String regexp;

    @Override
    public void initialize(Phone constraintAnnotation) {
        regexp = constraintAnnotation.regexp();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 不限制非空
        if (value == null) {
            return true;
        }
        return value.matches(regexp);
    }
}
