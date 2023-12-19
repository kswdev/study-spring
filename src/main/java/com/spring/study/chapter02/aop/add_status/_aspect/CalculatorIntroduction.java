package com.spring.study.chapter02.aop.add_status._aspect;

import com.spring.study.chapter02.aop.add_status._interface.Counter;
import com.spring.study.chapter02.aop.add_status.impl.CounterImpl;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CalculatorIntroduction {

    @DeclareParents(
            value = "com.spring.study.chapter02.aop.calculation._interface.*CalculatorImpl",
            defaultImpl = CounterImpl.class
    )
    public Counter counter;

    @After("execution(* com.spring.study.chapter02.aop.calculation._interface.*Calculator.*(..)) && this(counter)")
    public void increaseCount(Counter counter) {
        counter.increase();
    }
}
