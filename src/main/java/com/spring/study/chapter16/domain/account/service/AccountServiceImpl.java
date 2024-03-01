package com.spring.study.chapter16.domain.account.service;

import com.spring.study.chapter16.domain.account.entity.Account;
import com.spring.study.chapter16.domain.account.repository.AccountRepository;
import org.springframework.stereotype.Service;

//@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        System.out.println("생성!");
        this.accountRepository = accountRepository;
    }

    @Override
    public void createAccount(String accountNo) {
        accountRepository.createAccount(new Account(accountNo, 0));
    }

    @Override
    public void removeAccount(String accountNo) {
        Account account = accountRepository.findAccount(accountNo);
        accountRepository.removeAccount(account);
    }

    @Override
    public void deposit(String accountNo, double amount) {
        Account account = accountRepository.findAccount(accountNo);
        System.out.println(account.getBalance());
        account.setBalance(account.getBalance() + amount);
        accountRepository.updateAccount(account);
    }

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
