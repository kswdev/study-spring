package com.spring.study.chapter14.post.back;

import com.spring.study.chapter14.domain.mail.Mail;
import org.springframework.jms.annotation.JmsListener;

import java.util.Map;

public class MailListener {
    @JmsListener(destination = "mail.queue")
    public void onMessage(Map map) {

        Mail mail = Mail.builder()
                .mailId((String) map.get("mailId"))
                .country((String) map.get("country"))
                .weight((Double) map.get("weight"))
                .build();

        displqyMail(mail);
    }

    private void displqyMail(Mail mail) {
        System.out.println("Mail #" + mail.getMailId() + " received");
    }
}
