package com.spring.study.chapter16.calculator;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleInterestCalculatorTest {

    private InterestCalculator interestCalculator;

    @BeforeEach //junit5 에서는 @Before 대신 @BeforeEach 사용
    public void init() {
        interestCalculator = new SimpleInterestCalculator();
        interestCalculator.setRate(0.05);
    }

    @Test
    void calculate() {
        double interest = interestCalculator.calculate(10000, 2);
        assertEquals(1000.0, interest, 0);
    }

    @Test
    void illegalCalculate() {
        assertThrows(IllegalArgumentException.class,
                () -> interestCalculator.calculate(-10000, 2));
    }

}