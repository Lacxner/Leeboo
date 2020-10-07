package com.gzy.leeboo.exception;

import com.gzy.leeboo.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * <h1>全局异常处理器</h1>
 * 用于拦截所有 Controller 类（也可以缩小范围）中方法抛出的异常，并可以针对不同异常做特定的处理。
 */
@RestControllerAdvice
public class GlobalExceptionHandler{
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Spring Validation 校验异常
     * @return 异常错误信息
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();
        String defaultMessage = fieldError.getDefaultMessage();
        LOGGER.warn("可能是非法请求，不合法属性[" + defaultMessage + "]");
        return Result.failure().message("错误");
    }

    /**
     * Spring Validation 校验异常
     * @return 异常错误信息
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result handleConstraintViolationException(ConstraintViolationException constraintViolationException) {
        String message = constraintViolationException.getMessage();
        LOGGER.warn("可能是非法请求，错误原因[" + message + "]");
        return Result.failure().message(message);
    }
}
