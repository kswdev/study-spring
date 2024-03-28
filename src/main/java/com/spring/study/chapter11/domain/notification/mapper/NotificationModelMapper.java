package com.spring.study.chapter11.domain.notification.mapper;

import com.spring.study.chapter11.domain.booking.entity.BookingEntity;
import com.spring.study.chapter11.domain.notification.entity.NotificationEntity;
import com.spring.study.chapter11.domain.notification.entity.NotificationEvent;
import com.spring.study.chapter11.util.LocalDateTimeUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NotificationModelMapper {
    NotificationModelMapper INSTANCE = Mappers.getMapper(NotificationModelMapper.class);

    // 필드명이 같지 않거나 custom하게 매핑해주기 위해서는 @Mapping을 추가해주면 됩니다.
    @Mapping(target = "text", source = "bookingEntity.startedAt", qualifiedByName = "text")
    @Mapping(target = "uuid", source = "bookingEntity.userEntity.uuid")
    NotificationEntity toNotificationEntity(BookingEntity bookingEntity, NotificationEvent event);

    // 알람 보낼 메시지 생성
    @Named("text")
    default String text(LocalDateTime startedAt) {
        return String.format("안녕하세요. %s 수업 시작합니다. 수업 전 출석 체크 부탁드립니다. \uD83D\uDE0A", LocalDateTimeUtils.format(startedAt));

    }

}