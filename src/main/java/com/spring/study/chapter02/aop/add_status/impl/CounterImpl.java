package com.spring.study.chapter02.aop.add_status.impl;

import com.spring.study.chapter02.aop.add_status._interface.Counter;

public class CounterImpl implements Counter {

    private int count;

    @Override
    public void increase() {
        count++;
    }

    @Override
    public int getCount() {
        return count;
    }
}
