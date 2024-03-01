package com.spring.study.chapter16.application;

import com.spring.study.chapter16.domain.account.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Controller
class DepositControllerTest {

    private static final String TEST_ACCOUNT_NO = "1234";
    private static final double TEST_AMOUNT = 50;
    private AccountService accountService;
    private DepositController depositController;

    @BeforeEach
    void init() {
        accountService = mock(AccountService.class);
        depositController = new DepositController(accountService);
    }

    @Test
    void deposit() {
        //when
        when(accountService.getBalance(TEST_ACCOUNT_NO)).thenReturn(150.0);
        ModelMap model = new ModelMap();

        //then
        String viewName =
                depositController.deposit(TEST_ACCOUNT_NO, TEST_AMOUNT, model);

        assertEquals(viewName, "success");
        assertEquals(model.get("accountNo"), TEST_ACCOUNT_NO);
        assertEquals(model.get("balance"), 150.0);
    }
}