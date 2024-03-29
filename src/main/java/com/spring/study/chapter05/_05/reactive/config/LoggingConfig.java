package com.spring.study.chapter05._05.reactive.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.spring.study.chapter05.config.aop")
public class LoggingConfig {
}
