package com.spring.study.chapter05.domain.reservation.service;

import com.spring.study.chapter05.domain.player.Player;
import com.spring.study.chapter05.domain.reservation.Reservation;
import com.spring.study.chapter05.domain.sports.SportType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class ReservationServiceImpl implements ReservationService {

    public static final SportType TENNIS = new SportType(1, "Tennis");
    public static final SportType SOCCER = new SportType(2, "Soccer");
    private final List<Reservation> reservations = new ArrayList<>();

    public ReservationServiceImpl() {
        reservations.add(new Reservation("Tennis #1", new Date(2001, 1, 3), 20, new Player("Roger", "N/A"), TENNIS));
        reservations.add(new Reservation("Tennis #1", new Date(2001, 1, 3), 20, new Player("Roger", "N/A"), TENNIS));
        reservations.add(new Reservation("Tennis #1", new Date(2001, 1, 3), 20, new Player("Roger", "N/A"), TENNIS));
        reservations.add(new Reservation("Tennis #1", new Date(2001, 1, 3), 20, new Player("Roger", "N/A"), TENNIS));
        reservations.add(new Reservation("Tennis #1", new Date(2001, 1, 3), 20, new Player("Roger", "N/A"), TENNIS));
        reservations.add(new Reservation("Tennis #1", new Date(2001, 1, 3), 20, new Player("Roger", "N/A"), TENNIS));
        reservations.add(new Reservation("Tennis #1", new Date(2001, 1, 3), 20, new Player("Roger", "N/A"), TENNIS));
        reservations.add(new Reservation("Tennis #1", new Date(2001, 1, 3), 20, new Player("Roger", "N/A"), TENNIS));
        reservations.add(new Reservation("Tennis #1", new Date(2001, 1, 3), 20, new Player("Roger", "N/A"), TENNIS));
        reservations.add(new Reservation("Tennis #1", new Date(2001, 1, 3), 20, new Player("Roger", "N/A"), TENNIS));
        reservations.add(new Reservation("Tennis #1", new Date(2001, 1, 3), 20, new Player("Roger", "N/A"), TENNIS));
        reservations.add(new Reservation("Tennis #1", new Date(2001, 1, 3), 20, new Player("Roger", "N/A"), TENNIS));
        reservations.add(new Reservation("Tennis #1", new Date(2001, 1, 3), 20, new Player("Roger", "N/A"), TENNIS));
        reservations.add(new Reservation("Tennis #1", new Date(2001, 1, 3), 20, new Player("Roger", "N/A"), TENNIS));
        reservations.add(new Reservation("Tennis #1", new Date(2001, 1, 3), 20, new Player("Roger", "N/A"), TENNIS));
        reservations.add(new Reservation("Tennis #1", new Date(2001, 1, 3), 20, new Player("Roger", "N/A"), TENNIS));
        reservations.add(new Reservation("Tennis #1", new Date(2001, 1, 3), 20, new Player("Roger", "N/A"), TENNIS));
        reservations.add(new Reservation("Tennis #1", new Date(2001, 1, 3), 20, new Player("Roger", "N/A"), TENNIS));
        reservations.add(new Reservation("Tennis #1", new Date(2001, 1, 3), 20, new Player("Roger", "N/A"), TENNIS));
        reservations.add(new Reservation("Tennis #1", new Date(2001, 1, 3), 20, new Player("Roger", "N/A"), TENNIS));
        reservations.add(new Reservation("Tennis #1", new Date(2001, 1, 3), 20, new Player("Roger", "N/A"), TENNIS));
        reservations.add(new Reservation("Tennis #1", new Date(2001, 1, 3), 20, new Player("Roger", "N/A"), TENNIS));
        reservations.add(new Reservation("Tennis #2", new Date(2001, 1, 4), 20, new Player("Jogn", "N/A"), TENNIS));
    }

    @Override
    public List<Reservation> query(String courtName) {
        return this.reservations.stream()
                .filter(reservation -> Objects.equals(reservation.getSportType().getName(), courtName))
                .toList();
    }
}
