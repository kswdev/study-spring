package com.spring.study.chapter02.aop.calculation._aspect.log;

import com.spring.study.chapter02.aop.calculation.calculator.MaxCalculator;
import com.spring.study.chapter02.aop.calculation.calculator.MaxCalculatorImpl;
import com.spring.study.chapter02.aop.calculation.calculator.MinCalculator;
import com.spring.study.chapter02.aop.calculation.calculator.MinCalculatorImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class CalculatorLoggingAspect {

    private final Log log = LogFactory.getLog(this.getClass());
    @Pointcut("execution(* *.*(..))")
    private void loggingOperation() {}

/*
    @Before("execution(* com.spring.study.chapter02.aop.calculation._interface.ArithmeticCalculator.add(..))")
    public void logBefore() {
        log.info("The method add() begins");
    }
*/

/*
    @Before("execution(* *.*(..))")
    public void loggBefore(JoinPoint joinPoint) {
        log.info("The method " + joinPoint.getSignature().getName()
                + "() begins with " + Arrays.toString(joinPoint.getArgs()));
    }
*/
/*

    @After("execution(* *.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        log.info("The method " + joinPoint.getSignature().getName()
                + "() ends");
    }
*/
/*
    @AfterReturning(
            pointcut = "execution(* *.*(..))",
            returning = "res"
    )
    public void logAfterReturning(JoinPoint joinPoint, Object res) {
        log.info("The method "+ joinPoint.getSignature().getName() +
                 "() ends with " + res);
    }
*/
/*
    @AfterThrowing(
            pointcut = "execution(* *.*(..))",
            throwing = "res"
    )
    public void logAfterReturning(JoinPoint joinPoint, Throwable res) {
        log.info("The method "+ joinPoint.getSignature().getName() +
                "() ends with " + res);
    }
*/

    @Around("loggingOperation()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("the method() {}" + joinPoint.getSignature().getName()
                +" begins with {}" + Arrays.toString(joinPoint.getArgs()));

        try{
            Object result = joinPoint.proceed();
            log.info("the method : {}" + joinPoint.getSignature().getName()
                   + "ends with : {}" + result);
            return result;
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument : {}" + Arrays.toString(joinPoint.getArgs())
                    + "method with : {}" + joinPoint.getSignature().getName());

            throw e;
        }
    }

    @DeclareParents(
            value = "com.spring.study.chapter02.aop.calculation._interface.ArithmeticCalculatorImpl",
            defaultImpl = MaxCalculatorImpl.class
    )
    public MaxCalculator maxCalculator;

    @DeclareParents(
            value = "com.spring.study.chapter02.aop.calculation._interface.ArithmeticCalculatorImpl",
            defaultImpl = MinCalculatorImpl.class
    )
    public MinCalculator minCalculator;
}
