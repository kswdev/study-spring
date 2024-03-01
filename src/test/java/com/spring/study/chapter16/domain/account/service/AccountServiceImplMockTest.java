package com.spring.study.chapter16.domain.account.service;

import com.spring.study.chapter16.domain.account.entity.Account;
import com.spring.study.chapter16.domain.account.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountServiceImplMockTest {

    private static final String TEST_ACCOUNT_NO = "1234";

    private AccountRepository accountRepository;
    private AccountService accountService;

    @BeforeEach
    public void init() {
        accountRepository = mock(AccountRepository.class);
        accountService = new AccountServiceImpl(accountRepository);
    }

    @Test
    void deposit() {

        //설정
        Account account = new Account(TEST_ACCOUNT_NO, 100);
        when(accountRepository.findAccount(TEST_ACCOUNT_NO)).thenReturn(account);

        //실행
        accountService.deposit(TEST_ACCOUNT_NO, 50);

        //확인
        verify(accountRepository, times(1)).findAccount(any(String.class));
        verify(accountRepository, times(1)).updateAccount(account);
    }

    @Test
    void withdrawWithSufficientBalance() {
        // Given
        Account account = new Account(TEST_ACCOUNT_NO, 100);
        when(accountRepository.findAccount(TEST_ACCOUNT_NO)).thenReturn(account);

        // When
        accountService.withdraw(TEST_ACCOUNT_NO, 50);

        // Then
        verify(accountRepository, times(1)).findAccount(any(String.class));
        verify(accountRepository, times(1)).updateAccount(account);
    }

    @Test
    void withdrawWithInsufficientBalance() {
        // Given
        Account account = new Account(TEST_ACCOUNT_NO, 100);
        when(accountRepository.findAccount(TEST_ACCOUNT_NO)).thenReturn(account);

        // When
        assertThrows(IllegalArgumentException.class,
                () -> accountService.withdraw(TEST_ACCOUNT_NO, 150));
    }
}