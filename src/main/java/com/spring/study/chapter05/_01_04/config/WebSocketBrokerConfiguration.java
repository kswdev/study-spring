package com.spring.study.chapter05._01_04.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@ComponentScan("com.spring.study.chapter05.application")
public class WebSocketBrokerConfiguration implements WebSocketMessageBrokerConfigurer {

    // /topic으로 시작하는 메시지는 지금 등록된 중개기로
    // /app으로 시작하는 메시지는 @Messagemapping을 붙인 핸들러 메서드로
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");
    }

    // 수신된 STOMP 메시지를 리스닝하는 웹소켓 엔드포인트 등록
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/echo-endpoint");
    }
}
