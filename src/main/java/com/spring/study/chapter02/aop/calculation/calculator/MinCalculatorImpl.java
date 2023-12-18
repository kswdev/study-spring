package com.spring.study.chapter02.aop.calculation.calculator;

import java.util.function.DoubleBinaryOperator;

public class MinCalculatorImpl implements MinCalculator{
    @Override
    public DoubleBinaryOperator min(DoubleBinaryOperator binaryOperator) {
        System.out.println("min!");
        return binaryOperator;
    }
}
