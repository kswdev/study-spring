package com.spring.study.chapter14;


import com.spring.study.chapter14.config.BackOfficeConfiguration;
import com.spring.study.chapter14.domain.mail.Mail;
import com.spring.study.chapter14.post.back.BackOffice;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BackOfficeMain {

    public static void main(String[] args) {
        ApplicationContext context
                = new AnnotationConfigApplicationContext(BackOfficeConfiguration.class);

 /*       BackOffice backOffice = context.getBean(BackOffice.class);
        Mail mail = backOffice.receiveMail();
        System.out.println("Mail #" + mail.getMailId() + " received");*/
    }
}
