package com.spring.study.chapter14.config;

import com.spring.study.chapter14.post.back.BackOffice;
import com.spring.study.chapter14.post.back.JmsBackOfficeImpl;
import com.spring.study.chapter14.post.back.NotJmsBackOfficeImpl;
import com.spring.study.chapter14.post.front.JmsFrontDeskImpl;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

@Configuration
public class BackOfficeConfiguration {

    @Bean
    public ConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory("tcp://localhost:61616");
    }

    @Bean
    public Queue destination() {
        return new ActiveMQQueue("mail.queue");
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory());
        jmsTemplate.setReceiveTimeout(10000);
        jmsTemplate.setDefaultDestination(destination());
        return jmsTemplate;
    }

    @Bean
    public BackOffice backOffice() {
        //return new NotJmsBackOfficeImpl();
        JmsBackOfficeImpl jmsBackOffice = new JmsBackOfficeImpl();
        jmsBackOffice.setJmsTemplate(jmsTemplate());
        return jmsBackOffice;
    }
}
