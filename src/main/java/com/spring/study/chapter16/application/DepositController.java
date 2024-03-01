package com.spring.study.chapter16.application;

import com.spring.study.chapter16.domain.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class DepositController {

    private final AccountService accountService;

    @GetMapping("/")
    public String main() {
        return "welcome";
    }

    @GetMapping("/deposit")
    public String deposit(
            @RequestParam("accountNo") String accountNo,
            @RequestParam("amount") double amount,
            ModelMap model
    ) {
        accountService.deposit(accountNo, amount);
        model.addAttribute("accountNo", accountNo);
        model.addAttribute("balance", accountService.getBalance(accountNo));
        return "success";
    }
}
