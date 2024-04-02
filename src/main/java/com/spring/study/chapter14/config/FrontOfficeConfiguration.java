package com.spring.study.chapter14.config;

import com.spring.study.chapter14.post.converter.MailMessageConverter;
import com.spring.study.chapter14.post.front.FrontDesk;
import com.spring.study.chapter14.post.front.JmsFrontDeskImpl;
import com.spring.study.chapter14.post.front.NotJmsFrontDeskImpl;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;



@Configuration
public class FrontOfficeConfiguration {

    @Bean
    public ConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory("tcp://localhost:61616");
    }

    @Bean
    public Queue destination() {

        return new ActiveMQQueue("mail.queue");
        //return new ActiveMQQueue("recipe-15-2");
    }

    @Bean
    public JmsTemplate jmsTemplate() {

        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory());
        jmsTemplate.setDefaultDestination(destination());
        jmsTemplate.setMessageConverter(new MailMessageConverter());
        return jmsTemplate;
    }

    @Bean
    public FrontDesk frontDesk() {
        //return new NotJmsFrontDeskImpl();
        JmsFrontDeskImpl jmsFrontDesk = new JmsFrontDeskImpl();
        jmsFrontDesk.setJmsTemplate(jmsTemplate());
        return jmsFrontDesk;
    }
}
