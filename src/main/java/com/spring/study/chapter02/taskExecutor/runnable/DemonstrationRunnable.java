package com.spring.study.chapter02.taskExecutor.runnable;

import java.util.Date;

public class DemonstrationRunnable implements Runnable{
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName());
        System.out.printf("Hello at %s \n", new Date());
    }
}
