package com.spring.study.chapter02.taskExecutor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Runnable task = () -> {
            try {
                Thread.sleep(1000 * 6);
                System.out.println("Done sleeping for a minute, returning!");
            } catch (Exception ex) { }
        };

        ExecutorService service = Executors.newCachedThreadPool();

        if (service.submit(task, Boolean.TRUE).get().equals(Boolean.TRUE))
            System.out.println("Job has finished!");
    }
}
