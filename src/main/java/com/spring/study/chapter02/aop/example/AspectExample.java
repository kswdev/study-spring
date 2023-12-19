package com.spring.study.chapter02.aop.example;

import com.spring.study.chapter02.aop.add_status._interface.Counter;
import com.spring.study.chapter02.aop.calculation._interface.ArithmeticCalculator;
import com.spring.study.chapter02.aop.calculation._interface.UnitCalculator;
import com.spring.study.chapter02.aop.config.CalculatorConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AspectExample {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(CalculatorConfiguration.class);

        ArithmeticCalculator arithmeticCalculator =
                context.getBean("arithmeticCalculator", ArithmeticCalculator.class);
        arithmeticCalculator.add(1, 2);

        UnitCalculator unitCalculator =
                context.getBean("unitCalculator", UnitCalculator.class);
        unitCalculator.kilogramToPound(1);

        Counter arithmeticCounter = (Counter) arithmeticCalculator;
        System.out.println(arithmeticCounter.getCount());
    }
}
