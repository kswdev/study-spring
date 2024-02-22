package com.spring.study.chapter16.domain.account.repository;

import com.spring.study.chapter16.domain.account.entity.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
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

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        AccountRepository repository = new AccountRepositoryImpl(em);

        Account account = new Account();
        account.setAccountNo("EXISTING_ACCOUNT_NO");
        account.setBalance(0.2);

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            repository.createAccount(account);
            tx.commit();
        } catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
