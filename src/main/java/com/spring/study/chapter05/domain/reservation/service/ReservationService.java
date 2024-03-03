package com.spring.study.chapter05.domain.reservation.service;

import com.spring.study.chapter05.domain.reservation.Reservation;

import java.util.List;

public interface ReservationService {
    List<Reservation> query(String courtName);
}
