package com.spring.study.chapter02.shop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ProfileExample {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext();

        context.getEnvironment().setActiveProfiles("global", "winter");
        context.scan("com.spring.study.chapter02.shop");
        context.refresh();
    }
}
