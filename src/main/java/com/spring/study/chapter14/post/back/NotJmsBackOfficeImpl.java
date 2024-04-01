package com.spring.study.chapter14.post.back;

import com.spring.study.chapter14.domain.mail.Mail;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

import javax.jms.*;

public class NotJmsBackOfficeImpl implements BackOffice{
    @Override
    public Mail receiveMail() {
        ConnectionFactory cf = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Destination destination = new ActiveMQQueue("mail.queue");

        Connection conn = null;
        try {
            conn = cf.createConnection();
            Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageConsumer consumer = session.createConsumer(destination);

            conn.start();
            System.out.println("BackOffice start!");

            MapMessage message = (MapMessage) consumer.receive();

            Mail mail = Mail.builder()
                    .mailId(message.getString("mailId"))
                    .country(message.getString("country"))
                    .weight(message.getDouble("weight"))
                    .build();

            session.close();
            return mail;
        } catch (JMSException e) {
            throw new RuntimeException();
        } finally {
            if(conn != null) {
                try {
                    conn.close();
                } catch (JMSException e) {}
            }
        }
    }
}
