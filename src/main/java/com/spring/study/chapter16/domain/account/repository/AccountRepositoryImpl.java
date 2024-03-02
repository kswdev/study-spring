package com.spring.study.chapter16.domain.account.repository;

import com.spring.study.chapter16.domain.account.entity.Account;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@RequiredArgsConstructor
@Repository("accountRepository")
public class AccountRepositoryImpl implements AccountRepository {

    @Setter
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createAccount(Account account) {
        Account findAccount = findAccount(account.getAccountNo());
        if (!Objects.isNull(findAccount)) {
            throw new IllegalArgumentException("이미 있는 계좌입니다.");
        }

        entityManager.persist(account);
    }

    @Override
    public void updateAccount(Account account) {
        Account findAccount = findAccount(account.getAccountNo());
        if (Objects.isNull(findAccount)) {
            throw new NoSuchElementException("없는 계좌입니다.");
        }

        entityManager.merge(account);
    }

    @Override
    public void removeAccount(Account account) {
        Account findAccount = findAccount(account.getAccountNo());
        if (Objects.isNull(findAccount)) {
            throw new NoSuchElementException("없는 계좌입니다.");
        }

        entityManager.remove(account);
    }

    @Override
    public Account findAccount(String accountNo) {
        List<Account> accountList = entityManager.createQuery("SELECT a FROM Account a WHERE a.accountNo = :accountNo", Account.class)
                .setParameter("accountNo", accountNo)
                .getResultList();

        if (accountList.isEmpty()) return null;
        return accountList.get(0);
    }
}
