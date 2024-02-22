package com.spring.study.chapter16.domain.account.repository;

import com.spring.study.chapter16.domain.account.entity.Account;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@Slf4j
class AccountRepositoryImplTest {

    private AccountRepository accountRepository;
    private EntityManagerFactory emf;
    private EntityManager em;
    EntityTransaction tx;
    private static final String EXISTING_ACCOUNT_NO = "A";
    private static final String NEW_ACCOUNT_NO = "B";

    @BeforeEach
    void init() {
        emf = Persistence.createEntityManagerFactory("hello");
        em  = emf.createEntityManager();
        tx  = em.getTransaction();
        accountRepository = new AccountRepositoryImpl(em);
        tx.begin();
    }

    @AfterEach
    void close() {
        em.close();
        emf.close();
    }

    @Test
    void accountExists() {
        Account account = new Account();
        account.setAccountNo(EXISTING_ACCOUNT_NO);
        account.setBalance(0.2);

        try {
            accountRepository.createAccount(account);
            assertEquals(1, accountRepository.findAccount(EXISTING_ACCOUNT_NO).size());
            assertEquals(0, accountRepository.findAccount(NEW_ACCOUNT_NO).size());
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }
    }
}