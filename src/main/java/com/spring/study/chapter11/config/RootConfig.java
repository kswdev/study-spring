package com.spring.study.chapter11.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(JpaConfig.class)
@ComponentScan("com.spring.study.chapter11.domain")
@Configuration  //초기에는 셋팅할 게 없다.
public class RootConfig {
}
