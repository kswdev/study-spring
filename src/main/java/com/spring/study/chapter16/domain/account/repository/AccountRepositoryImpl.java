package com.spring.study.chapter16.domain.account.repository;

import com.spring.study.chapter16.domain.account.entity.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
@Repository
public class AccountRepositoryImpl implements AccountRepository{

    private final EntityManager em;

    @Override
    public void createAccount(Account account) {
        em.persist(account);
    }

    @Override
    public void updateAccount(Account account) {
        em.merge(account);
    }

    @Override
    public void removeAccount(Account account) {
        em.remove(account);
    }

    @Override
    public Account findAccount(String accountNo) {
        return em.createQuery("SELECT a FROM Account a WHERE a.accountNo = :accountNo", Account.class)
                .setParameter("accountNo", accountNo)
                .getSingleResult();
    }
}
