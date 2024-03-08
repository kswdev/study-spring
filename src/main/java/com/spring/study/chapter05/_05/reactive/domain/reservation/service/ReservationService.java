package com.spring.study.chapter05._05.reactive.domain.reservation.service;

import com.spring.study.chapter05._05.reactive.domain.reservation.entity.Reservation;

import java.util.List;

public interface ReservationService {
    List<Reservation> query(String courtName);
}
