package com.spring.study.chapter16.domain.account.repository;

import com.spring.study.chapter16.domain.account.entity.Account;

public interface AccountRepository {
    void createAccount(Account account);
    void updateAccount(Account account);
    void removeAccount(Account account);
    Account findAccount(String accountNo);
}
