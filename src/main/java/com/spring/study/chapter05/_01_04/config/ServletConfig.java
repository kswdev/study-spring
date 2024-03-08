package com.spring.study.chapter05._01_04.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;


@Configuration
@EnableWebMvc
@ComponentScan("com.spring.study.chapter05")
public class ServletConfig implements WebMvcConfigurer {

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        WebMvcConfigurer.super.configureViewResolvers(registry);
        // ViewResolver 설정
        registry.jsp("/webapp/WEB-INF/template/", ".jsp");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);
        // Static Resources 설정
        registry.addResourceHandler("/**").addResourceLocations("/resources/");
    }
}
