package com.spring.study.chapter16.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@Configuration
public class HibernateConfig {

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        return Persistence.createEntityManagerFactory("hello");
    }
    @Bean
    public EntityManager entityManager() {
        return entityManagerFactory().createEntityManager();
    }
    @Bean
    public EntityTransaction entityTransaction() {
        return entityManager().getTransaction();
    }
}
