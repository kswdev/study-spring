package com.spring.study.chapter16.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Import(value = {HibernateConfig.class})
@Configuration
@EnableWebMvc
@ComponentScan("com.spring.study.chapter16")
public class ServletConfig implements WebMvcConfigurer {

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        System.out.println("register");
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
