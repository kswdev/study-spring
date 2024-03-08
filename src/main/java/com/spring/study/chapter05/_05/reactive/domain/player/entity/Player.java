package com.spring.study.chapter05._05.reactive.domain.player.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@SequenceGenerator(
        name = "PLAYER_SEQ_GENERATOR",
        sequenceName = "PLAYER_SEQ"
)
@Getter @Setter
public class Player {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "PLAYER_SEQ_GENERATOR"
    )
    private Long id;
    private String name;
    private String phone;

    public Player(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}
