package com.spring.study.chapter05.domain.reservation;

import com.spring.study.chapter05.domain.player.Player;
import com.spring.study.chapter05.domain.sports.SportType;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@Getter @Setter
public class Reservation {
    private String courtName;
    private Date date;
    private int hour;
    private Player player;
    private SportType sportType;

    @Builder
    public Reservation(String courtName, Date date, int hour, Player player, SportType sportType) {
        this.courtName = courtName;
        this.date = date;
        this.hour = hour;
        this.player = player;
        this.sportType = sportType;
    }
}
