package com.spring.study.chapter15;

import com.spring.study.chapter15.config.IntegrationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context
                = new AnnotationConfigApplicationContext(IntegrationConfiguration.class);
    }
}
