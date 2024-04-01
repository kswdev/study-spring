package com.spring.study.chapter14.domain.mail;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Mail {

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
