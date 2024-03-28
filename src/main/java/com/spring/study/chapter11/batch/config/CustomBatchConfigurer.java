package com.spring.study.chapter11.batch.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CustomBatchConfigurer extends DefaultBatchConfigurer {

    private final TaskExecutor taskExecutor;

    @Override
    protected JobLauncher createJobLauncher() throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(getJobRepository());
        jobLauncher.setTaskExecutor(this.taskExecutor);
        jobLauncher.afterPropertiesSet();
        return jobLauncher;
    }
}
