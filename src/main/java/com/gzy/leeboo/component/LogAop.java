package com.gzy.leeboo.component;

import com.gzy.leeboo.controller.ChatController;
import com.gzy.leeboo.controller.MenuController;
import com.gzy.leeboo.entity.Hr;
import com.gzy.leeboo.entity.Log;
import com.gzy.leeboo.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * 使用AOP实现的系统日志记录，排除了 {@link MenuController} 和 {@link ChatController} 控制器中的所有方法。
 */
@Component
@Aspect
public class LogAop {
    // 访问时间
    private LocalDateTime visitTime;
    // 访问的类
    private Class clazz;
    // 访问的方法
    private Method method;

    private LogService LogService;

    @Autowired
    public void setLogService(LogService LogService) {
        this.LogService =LogService;
    }

    @Pointcut("execution(* com.gzy.leeboo.controller.*.add*(..))")
    public void addPointcut() {}
    @Pointcut("execution(* com.gzy.leeboo.controller.*.update*(..))")
    public void updatePointcut() {}
    @Pointcut("execution(* com.gzy.leeboo.controller.*.delete*(..))")
    public void deletePointcut() {}
    @Pointcut("!execution(* com.gzy.leeboo.controller.MenuController.*(..))")
    public void exceptMenu() {}
    @Pointcut("!execution(* com.gzy.leeboo.controller.ChatController.*(..))")
    public void exceptChat() {}
    @Pointcut("(addPointcut() || updatePointcut() || deletePointcut()) && exceptMenu() && exceptChat()")
    public void finalPointcut() {}

    @Before("finalPointcut()")
    public void doBefore(JoinPoint joinPoint) {
        // 获取访问时间
        visitTime = LocalDateTime.now();
        // 获取访问的类
        clazz = joinPoint.getTarget().getClass();
        // 获取访问方法的名称
        String methodName = joinPoint.getSignature().getName();
        // 获取访问的方法参数
        Object[] args = joinPoint.getArgs();
        // 获取访问的方法
        try {
            if (args == null || args.length == 0) {
                method = clazz.getDeclaredMethod(methodName);
            } else {
                // 获取每个参数的Class类型并存入一个数组
                Class[] classes = new Class[args.length];
                for (int i = 0; i < args.length; i++) {
                    classes[i] = args[i].getClass();
                }
                method = clazz.getDeclaredMethod(methodName, classes);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @After("finalPointcut()")
    public void doAfter(JoinPoint joinPoint) {
        // 获取方法执行时间
        long executionTime = LocalDateTime.now().toInstant(ZoneOffset.ofHours(8)).toEpochMilli() - visitTime.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
        // 获取访问方式
        String pattern = null;
        if (clazz != null && method != null) {
            // 获取方法上的映射路径
            GetMapping methodGetAnnotation =  method.getAnnotation(GetMapping.class);
            PostMapping methodPostAnnotation =  method.getAnnotation(PostMapping.class);
            PutMapping methodPutAnnotation =  method.getAnnotation(PutMapping.class);
            DeleteMapping methodDeleteAnnotation =  method.getAnnotation(DeleteMapping.class);
            if (methodGetAnnotation != null) {
                pattern = "查询";
            } else if (methodPostAnnotation != null) {
                pattern = "添加";
            } else if (methodPutAnnotation != null) {
                pattern = "修改";
            } else if (methodDeleteAnnotation != null) {
                pattern = "删除";
            }
        }
        // 获取访问的IP
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String ip = request.getRemoteAddr();
        // 获取访问的URL
        String url = request.getServletPath();
        // 获取当前操作的用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            Hr hr = (Hr) authentication.getPrincipal();
            // 日志信息的持久化
            Log Log = new Log(method.getName(), pattern, url, hr.getUsername(), ip, visitTime, executionTime);
            LogService.addLog(Log);
        }
    }
}
