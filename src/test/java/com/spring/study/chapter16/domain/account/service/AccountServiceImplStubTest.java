package com.spring.study.chapter16.domain.account.service;

import com.spring.study.chapter16.domain.account.entity.Account;
import com.spring.study.chapter16.domain.account.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountServiceImplStubTest {

    private static final String TEST_ACCOUNT_NO = "1234";

    private AccountDaoStub accountDaoStub;
    private AccountService accountService;

    private static class AccountDaoStub implements AccountRepository {

        private String accountNo;
        private double balance;

        @Override
        public void createAccount(Account account) {}

        @Override
        public void updateAccount(Account account) {
            this.accountNo = account.getAccountNo();
            this.balance   = account.getBalance();
        }

        @Override
        public void removeAccount(Account account) {}

        @Override
        public Account findAccount(String accountNo) {
            return new Account(this.accountNo, this.balance);
        }
    }

    @BeforeEach
    public void init() {
        accountDaoStub = new AccountDaoStub();
        accountDaoStub.accountNo = TEST_ACCOUNT_NO;
        accountDaoStub.balance = 100;
        accountService = new AccountServiceImpl(accountDaoStub);
    }

    @Test
    void deposit() {
        accountService.deposit(TEST_ACCOUNT_NO, 50);
        assertEquals(TEST_ACCOUNT_NO, accountDaoStub.accountNo);
        assertEquals(150, accountDaoStub.balance, 0);
    }

    @Test
    void withdrawWithSufficientBalance() {
        accountService.withdraw(TEST_ACCOUNT_NO, 50);
        assertEquals(TEST_ACCOUNT_NO, accountDaoStub.accountNo);
        assertEquals(50, accountDaoStub.balance, 0);
    }

    @Test
    void withdrawWithInsufficientBalance() {
        assertThrows(IllegalArgumentException.class,
                () -> accountService.withdraw(TEST_ACCOUNT_NO, 150));
    }
}