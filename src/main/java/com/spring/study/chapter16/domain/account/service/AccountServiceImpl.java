package com.spring.study.chapter16.domain.account.service;

import com.spring.study.chapter16.domain.account.entity.Account;
import com.spring.study.chapter16.domain.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;

    @Transactional
    @Override
    public void createAccount(String accountNo) {
        accountRepository.createAccount(new Account(accountNo, 0));
    }

    @Transactional
    @Override
    public void removeAccount(String accountNo) {
        Account account = accountRepository.findAccount(accountNo);
        accountRepository.removeAccount(account);
    }

    @Transactional
    @Override
    public void deposit(String accountNo, double amount) {
        Account account = accountRepository.findAccount(accountNo);
        account.setBalance(account.getBalance() + amount);
        accountRepository.updateAccount(account);
    }

    @Transactional
    @Override
    public void withdraw(String accountNo, double amount) {
        Account account = accountRepository.findAccount(accountNo);

        if (account.getBalance() < amount)
            throw new IllegalArgumentException("예금 금액이 부족합니다.");

        account.setBalance(account.getBalance() - amount);
        accountRepository.updateAccount(account);
    }

    @Override
    public double getBalance(String accountNo) {
        return accountRepository.findAccount(accountNo).getBalance();
    }
}
