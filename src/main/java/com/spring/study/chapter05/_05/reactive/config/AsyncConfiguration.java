package com.spring.study.chapter05._05.reactive.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.spring.study.chapter05._01_04.common.interceptor.MesurementInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
public class AsyncConfiguration extends WebMvcConfigurationSupport {

    @Override
    protected void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setDefaultTimeout(6000);
        configurer.setTaskExecutor(mvcTaskExecutor());
    }

    @Bean
    public ThreadPoolTaskExecutor mvcTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(3);
        taskExecutor.setMaxPoolSize(5);
        taskExecutor.setQueueCapacity(10);
        taskExecutor.setThreadGroupName("mvc-executor");
        return taskExecutor;
    }

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/webapp/WEB-INF/template/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter(objectMapper);
        converters.add(jackson2HttpMessageConverter);
        converters.add(stringHttpMessageConverter());
    }

    @Bean
    public StringHttpMessageConverter stringHttpMessageConverter() {
        return new StringHttpMessageConverter();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MesurementInterceptor());
    }

    @Bean
    public ServletServerContainerFactoryBean configureWebSocketContainer() {
        ServletServerContainerFactoryBean factory = new ServletServerContainerFactoryBean();
        factory.setMaxBinaryMessageBufferSize(16384);
        factory.setMaxTextMessageBufferSize(16384);
        factory.setMaxSessionIdleTimeout(TimeUnit.MINUTES.convert(30, TimeUnit.MILLISECONDS));
        factory.setAsyncSendTimeout(TimeUnit.SECONDS.convert(5, TimeUnit.MILLISECONDS));
        return factory;
    }
}
