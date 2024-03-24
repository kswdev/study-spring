package com.spring.study.chapter11.batch.job.notification;

import com.spring.study.chapter11.domain.notification.entity.NotificationEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
public class SendNotificationItemWriter implements ItemWriter<NotificationEntity> {

    @Override
    public void write(List<? extends NotificationEntity> notificationEntities) throws Exception {
        int count = 0;

        for (NotificationEntity notificationEntity : notificationEntities) {
            //TODO 메세지 보내기

        }
        log.info("SendNotificationItemWriter - write: 수업 전 알람 {}/{}건 전송 성공", count, notificationEntities.size());

    }

}
