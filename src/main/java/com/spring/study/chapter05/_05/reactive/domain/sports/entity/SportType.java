package com.spring.study.chapter05._05.reactive.domain.sports.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@SequenceGenerator(
        name = "SPORT_TYPE_SEQ_GENERATOR",
        sequenceName = "PLAYER_SEQ"
)
@Getter @Setter
public class SportType {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SPORT_TYPE_SEQ_GENERATOR"
    )
    private int id;
    private String name;

    public SportType(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
