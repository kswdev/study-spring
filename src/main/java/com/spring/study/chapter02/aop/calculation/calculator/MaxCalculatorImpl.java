package com.spring.study.chapter02.aop.calculation.calculator;

import java.util.function.DoubleBinaryOperator;

public class MaxCalculatorImpl implements MaxCalculator {
    @Override
    public DoubleBinaryOperator max(DoubleBinaryOperator binaryOperator) {
        System.out.println("max!");
        return binaryOperator;
    }
}
