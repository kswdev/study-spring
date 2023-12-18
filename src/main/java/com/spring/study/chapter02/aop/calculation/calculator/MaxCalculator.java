package com.spring.study.chapter02.aop.calculation.calculator;

import java.util.function.DoubleBinaryOperator;

@FunctionalInterface
public interface MaxCalculator {
    DoubleBinaryOperator max(DoubleBinaryOperator binaryOperator);
}
