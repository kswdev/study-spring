package com.spring.study.chapter05._05.reactive.domain.reservation.service;

import com.spring.study.chapter05._01_04.domain.sports.SportType;
import com.spring.study.chapter05._05.reactive.domain.reservation.entity.Reservation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ReservationServiceImpl implements ReservationService {

    public static final SportType TENNIS = new SportType(1, "Tennis");
    public static final SportType SOCCER = new SportType(2, "Soccer");
    private final List<Reservation> reservations = new ArrayList<>();


    @Override
    public List<Reservation> query(String courtName) {
        return this.reservations.stream()
                .filter(reservation -> Objects.equals(reservation.getSportType().getName(), courtName))
                .toList();
    }
}
