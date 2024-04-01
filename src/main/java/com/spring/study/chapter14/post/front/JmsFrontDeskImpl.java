package com.spring.study.chapter14.post.front;

import com.spring.study.chapter14.domain.mail.Mail;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.support.JmsGatewaySupport;

import javax.jms.MapMessage;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class JmsFrontDeskImpl extends JmsGatewaySupport implements FrontDesk{

    @Override
    public void sendMail(final Mail mail) {
        assert getJmsTemplate() != null;

        /*  message 객체에 직접 세팅
        getJmsTemplate().send(session -> {
                MapMessage message = session.createMapMessage();
                message.setString("mailId", mail.getMailId());
                message.setString("country", mail.getCountry());
                message.setDouble("weight", mail.getWeight());
                return message;
        });
        */

        // Map <-> MapMessage 변환을 처리하는 convertAndSend()
        Map<String, Object> map = new HashMap<>();
        map.put("mailId", mail.getMailId());
        map.put("country", mail.getCountry());
        map.put("weight", mail.getWeight());
        getJmsTemplate().convertAndSend(map);
    }
}
