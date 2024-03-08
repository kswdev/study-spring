package com.spring.study.chapter05._01_04.domain.sports;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class SportType {
    private int id;
    private String name;

    public SportType(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
