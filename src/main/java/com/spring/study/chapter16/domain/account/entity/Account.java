package com.spring.study.chapter16.domain.account.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Account {

    @Id @GeneratedValue
    private Long id;

    private String accountNo;
    private double balance;
}
