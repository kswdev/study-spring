package com.spring.study.chapter16.calculator;

public interface InterestCalculator {
    void setRate(double rate);
    double calculate(double amount, double year);
}
