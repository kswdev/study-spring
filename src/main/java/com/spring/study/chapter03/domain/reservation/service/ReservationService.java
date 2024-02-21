package com.spring.study.chapter03.domain.reservation.service;

import com.spring.study.chapter03.domain.reservation.Reservation;

import java.util.List;

public interface ReservationService {
    List<Reservation> query(String courtName);
}
