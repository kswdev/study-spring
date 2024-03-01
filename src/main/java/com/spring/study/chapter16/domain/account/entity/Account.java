package com.spring.study.chapter16.domain.account.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@SequenceGenerator(
        name = "ACCOUNT_SEQ_GENERATOR",
        sequenceName = "ACCOUNT_SEQ",
        initialValue = 1, allocationSize = 50
)
@Entity
@Getter @Setter
public class Account {

    @Id @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ACCOUNT_SEQ_GENERATOR"
    )
    private Long id;

    private String accountNo;
    private double balance;

    public Account() { }

    public Account(String accountNo, double balance) {
        this.accountNo = accountNo;
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account account)) return false;
        return Double.compare(account.getBalance(), getBalance()) == 0 && Objects.equals(getAccountNo(), account.getAccountNo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAccountNo(), getBalance());
    }
}
