package com.spring.study.chapter16.domain.account.repository;

import com.spring.study.chapter16.domain.account.entity.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Repository
public class AccountRepositoryImpl implements AccountRepository {

    private final EntityManager em;

    @Override
    public void createAccount(Account account) {
        List<Account> accountList = findAccount(account.getAccountNo());
        if (!accountList.isEmpty()) {
            throw new IllegalArgumentException("이미 있는 계좌입니다.");
        }

        em.persist(account);
    }

    @Override
    public void updateAccount(Account account) {
        List<Account> accountList = findAccount(account.getAccountNo());
        if (accountList.isEmpty()) {
            throw new NoSuchElementException("없는 계좌입니다.");
        }
        em.merge(account);
    }

    @Override
    public void removeAccount(Account account) {
        List<Account> accountList = findAccount(account.getAccountNo());
        if (accountList.isEmpty()) {
            throw new NoSuchElementException("없는 계좌입니다.");
        }
        em.remove(account);
    }

    @Override
    public List<Account> findAccount(String accountNo) {
        return em.createQuery("SELECT a FROM Account a WHERE a.accountNo = :accountNo", Account.class)
                .setParameter("accountNo", accountNo)
                .getResultList();
    }
}
