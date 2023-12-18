package com.spring.study.chapter02.aop.example;

import com.spring.study.chapter02.aop.calculation._interface.ArithmeticCalculator;
import com.spring.study.chapter02.aop.calculation.calculator.MaxCalculator;
import com.spring.study.chapter02.aop.calculation.calculator.MinCalculator;
import com.spring.study.chapter02.aop.config.CalculatorConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IntroductionExample {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(CalculatorConfiguration.class);

        ArithmeticCalculator arithmeticCalculator =
                context.getBean("arithmeticCalculator", ArithmeticCalculator.class);

        MaxCalculator maxCalculator = (MaxCalculator) arithmeticCalculator;
        MinCalculator minCalculator = (MinCalculator) arithmeticCalculator;
        System.out.println(maxCalculator.max(Math::max).applyAsDouble(1, 2));
        System.out.println(minCalculator.min(Math::min).applyAsDouble(1, 2));

    }
}
