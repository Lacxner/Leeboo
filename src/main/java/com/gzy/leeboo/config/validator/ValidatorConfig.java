package com.gzy.leeboo.config.validator;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * 用于 Spring Validation 相关参数
 */
@Configuration
public class ValidatorConfig {
    @Bean
    public Validator validator() {
        // 生成验证器工厂，并设置快速失败（Fail Fast）
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class).configure()
                .failFast(true)
                .buildValidatorFactory();
        // 通过验证器工厂获取验证器
        Validator validator = validatorFactory.getValidator();
        return validator;
    }
}
