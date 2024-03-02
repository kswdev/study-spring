package com.spring.study.chapter16.domain.account.service;

import com.spring.study.chapter16.config.ServletConfig;
import com.spring.study.chapter16.domain.account.repository.AccountRepository;
import com.spring.study.chapter16.domain.account.repository.AccountRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = ServletConfig.class)
@ExtendWith(SpringExtension.class)
class AccountServiceImplTest {

    private static final String TEST_ACCOUNT_NO = "1234";

    @Autowired
    private AccountService accountService;


    @BeforeEach
    public void init() {
        AccountRepositoryImpl repository = new AccountRepositoryImpl();
        repository.setEntityManager(Persistence.createEntityManagerFactory("hello").createEntityManager());
        accountService.createAccount(TEST_ACCOUNT_NO);
        accountService.deposit(TEST_ACCOUNT_NO, 100);
    }

    @Test
    void deposit() {
        accountService.deposit(TEST_ACCOUNT_NO, 50);
        assertEquals(150, accountService.getBalance(TEST_ACCOUNT_NO), 0);
    }

    @Test
    void withDraw() {
        accountService.withdraw(TEST_ACCOUNT_NO, 50);
        assertEquals(50, accountService.getBalance(TEST_ACCOUNT_NO), 0);
    }
}