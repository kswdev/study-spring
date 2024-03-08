package com.spring.study.chapter05._01_04.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class EchoHandler {

    @MessageMapping("/echo")
    @SendTo("/topic/echo")
    public String echo(String msg) {
        log.info("수신 완료");
        return "RECEIVED: " + msg;
    }
}