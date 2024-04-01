package com.spring.study.chapter14;

import com.spring.study.chapter14.config.FrontOfficeConfiguration;
import com.spring.study.chapter14.domain.mail.Mail;
import com.spring.study.chapter14.post.front.FrontDesk;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class FrontDeskMain {

    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(FrontOfficeConfiguration.class);

        FrontDesk frontDesk = context.getBean(FrontDesk.class);
        frontDesk.sendMail(new Mail("12345", "US", 1.5));
    }
}