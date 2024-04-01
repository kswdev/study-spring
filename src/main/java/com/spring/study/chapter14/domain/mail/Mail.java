package com.spring.study.chapter14.domain.mail;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Getter
public class Mail implements Serializable {

    private String mailId;
    private String country;
    private double weight;

    @Builder
    public Mail(String mailId, String country, double weight) {
        this.mailId = mailId;
        this.country = country;
        this.weight = weight;
    }
}
