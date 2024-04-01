package com.spring.study.chapter14.post.back;

import com.spring.study.chapter14.domain.mail.Mail;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.support.JmsGatewaySupport;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import java.util.Map;

@RequiredArgsConstructor
public class JmsBackOfficeImpl extends JmsGatewaySupport implements BackOffice{


    @Override
    public Mail receiveMail() {
        /*assert getJmsTemplate() != null;
        MapMessage message = (MapMessage) getJmsTemplate().receive();

        try {
            if (message == null)
                return null;

            Mail mail = Mail.builder()
                    .mailId(message.getString("mailId"))
                    .country(message.getString("country"))
                    .weight(message.getDouble("weight"))
                    .build();

            return mail;

        } catch (JMSException ignored) {}
        return null;*/

        assert getJmsTemplate() != null;
        Map map = (Map) getJmsTemplate().receiveAndConvert();
        Mail mail = Mail.builder()
                .mailId((String) map.get("mailId"))
                .country((String) map.get("coutnry"))
                .weight((Double) map.get("weight"))
                .build();

        return mail;
    }
}
