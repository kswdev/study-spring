package com.spring.study.chapter05._01_04.config.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
@Component
@Slf4j
public class LoggingAOP {

    @Before("execution(* com.spring.study.chapter05.application.*Controller.*(..))")
    public void appLogging(JoinPoint joinPoint) {
        log.info("[{}] timestamp: {}", getCurrentThreadName(), getCurrentTimestamp());
    }

    @AfterReturning(pointcut = "execution(* com.spring.study.chapter05.application.*Controller.*(..))", returning = "result")
    public void afterLogging(JoinPoint joinPoint, Object result) {
        log.info("[{}] return: {}", getCurrentThreadName(), result.toString());
    }

    @AfterThrowing(pointcut = "execution(* com.spring.study.chapter05.application.*Controller.*(..))", throwing = "exception")
    public void afterThrowing(JoinPoint joinPoint, Throwable exception) {
        log.error("[{}] throwing: {}", getCurrentThreadName(), exception.getMessage());
    }

    private String getCurrentTimestamp() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return format.format(new Date());
    }

    private String getCurrentThreadName() {
        return Thread.currentThread().getName();
    }
}
