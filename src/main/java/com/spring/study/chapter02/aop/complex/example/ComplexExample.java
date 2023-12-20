package com.spring.study.chapter02.aop.complex.example;

import com.spring.study.chapter02.aop.complex.Complex;
import com.spring.study.chapter02.aop.complex._interface.ComplexCalculator;
import com.spring.study.chapter02.aop.config.CalculatorConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ComplexExample {

    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(CalculatorConfiguration.class);

        ComplexCalculator complexCalculator =
                context.getBean("complexCalculator", ComplexCalculator.class);

        complexCalculator.add(new Complex(1, 2), new Complex(2, 3));
        complexCalculator.add(new Complex(5, 8), new Complex(2, 3));
    }
}
