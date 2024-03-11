package com.spring.study.chapter07.application;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {

    @GetMapping("/todos")
    public String home() {
        return "socket";
    }

    @GetMapping("/login")
    public String login() {
        return "index";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }
}
