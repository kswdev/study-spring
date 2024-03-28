package com.spring.study.chapter11;

import com.spring.study.chapter11.config.JpaConfig;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;

public class Main {

    public static void main(String[] args) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException, InterruptedException {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(JpaConfig.class);

        JobRegistry jobRegistry = context.getBean("jobRegistry", JobRegistry.class);
        JobLauncher jobLauncher = context.getBean("jobLauncher", JobLauncher.class);

        JobRepository jobRepository = context.getBean("jobRepository", JobRepository.class);

        System.out.println("JobRegistry: " + jobRegistry);
        System.out.println("jobLauncher: " + jobLauncher);
        System.out.println("jobRepository: " + jobRepository);

        Job job = context.getBean("sendNotificationBeforeClassJob", Job.class);
        JobExecution jobExecution = jobLauncher.run(job, new JobParametersBuilder()
                .addDate("date", new Date()).toJobParameters());

        BatchStatus batchStatus = jobExecution.getStatus();
        while(batchStatus.isRunning()) {
            System.out.println("Still running");
            Thread.sleep(10*1000);
        }
    }
}
