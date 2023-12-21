package com.spring.study.chapter02.taskExecutor;

import com.spring.study.chapter02.taskExecutor.runnable.DemonstrationRunnable;

import java.util.Date;
import java.util.concurrent.*;

public class ExecutorsDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Runnable task = new DemonstrationRunnable();

        ExecutorService cachedExecutorService =
                Executors.newCachedThreadPool();
        if (cachedExecutorService.submit(task).get() == null)
            System.out.printf("The cachedExecutorService has succeeded at %s \n", new Date());

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(100);
        if (fixedThreadPool.submit(task).get() == null)
            System.out.printf("fixedThreadPool has succeeded at %s \n", new Date());

        ExecutorService singleThreadExecutorService =
                Executors.newSingleThreadExecutor();
        if (singleThreadExecutorService.submit(task).get() == null)
            System.out.printf("singleThreadExecutorService has succeeded at %s \n", new Date());

        ExecutorService ex = Executors.newCachedThreadPool();
        if (ex.submit(task, Boolean.TRUE).get().equals(Boolean.TRUE))
            System.out.println("Job has finished!");

        ScheduledExecutorService scheduledExecutorService =
                Executors.newScheduledThreadPool(10);
        if (scheduledExecutorService.schedule(
                task, 30, TimeUnit.SECONDS).get() == null)
            System.out.printf("scheduledThreadExecutorService has succeeded at %s \n", new Date());

        scheduledExecutorService.scheduleAtFixedRate(task, 0, 5, TimeUnit.SECONDS);
    }
}
