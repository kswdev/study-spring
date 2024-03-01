package com.spring.study.chapter16.domain.account.service;

import com.spring.study.chapter16.domain.account.repository.AccountRepository;
import com.spring.study.chapter16.domain.account.repository.AccountRepositoryImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountServiceImplTest {

    private static final String TEST_ACCOUNT_NO = "1234";

    private EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction tx;
    private AccountService accountService;


    @BeforeEach
    public void init() {
        emf = Persistence.createEntityManagerFactory("hello");
        em = emf.createEntityManager();
        tx = em.getTransaction();
        AccountRepository accountRepository = new AccountRepositoryImpl(em);

        accountService = new AccountServiceImpl(accountRepository);

        tx.begin();

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

    @AfterEach
    void cleanup() {
        tx.commit();
        em.close();
        emf.close();
    }
}