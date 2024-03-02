package com.spring.study.chapter16.application;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/test")
    public String Home() {
        System.out.println("good!");
        return "welcomed";
    }
}
