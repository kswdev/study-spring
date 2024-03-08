package com.spring.study.chapter05._05.reactive.domain.reservation.entity;

import com.spring.study.chapter05._05.reactive.domain.player.entity.Player;
import com.spring.study.chapter05._05.reactive.domain.sports.entity.SportType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@SequenceGenerator(
        name = "RESERVATION_SEQ_GENERATOR",
        sequenceName = "RESERVATION_SEQ"
)
@Entity
@Getter @Setter
@Table(name = "COURT_RESERVATION")
public class Reservation {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "RESERVATION_SEQ_GENERATOR"
    )
    private Long id;
    private String courtName;
    private LocalDateTime reservationTime;
    private int hourTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private Player player;

    @ManyToOne(fetch = FetchType.LAZY)
    private SportType sportType;

    @Builder
    public Reservation(String courtName, LocalDateTime date, int hour, Player player, SportType sportType) {
        this.courtName = courtName;
        this.reservationTime = date;
        this.hourTime = hour;
        this.player = player;
        this.sportType = sportType;
    }
}
