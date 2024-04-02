package com.spring.study.chapter15.config;

import com.spring.study.chapter15.jms.processor.InboundHelloWorldJMSMessageProcessor;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.jms.dsl.Jms;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;

@Configuration
@EnableIntegration
@ComponentScan
public class IntegrationConfiguration {

    @Bean
    public CachingConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory("tcp://localhost:61616");
        return new CachingConnectionFactory(connectionFactory);
    }

    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
        System.out.println(connectionFactory.getClass());
        return new JmsTemplate(connectionFactory);
    }

    @Bean
    public InboundHelloWorldJMSMessageProcessor messageProcessor() {
        return new InboundHelloWorldJMSMessageProcessor();
    }

    /**
     * @param connectionFactory
     * @return
     *
     * 메세기자 시스템을 어덯게 흘러가는지 정의
     */
    public IntegrationFlow jmsInbound(ConnectionFactory connectionFactory) {
        return IntegrationFlows
                /* 어떤 종류의 하위 시스템과 어떤 방법으로 서통할지 알고있는 컴포넌트 */
                .from(Jms.messageDrivenChannelAdapter(connectionFactory)
                         .extractPayload(true)
                         /* recipe-15-2 로부터 메시지를 절달 받음 */
                         .destination("recipe-15-2"))
                /* 비즈니스 로직 */
                .handle(messageProcessor())
                .get();
    }
}
