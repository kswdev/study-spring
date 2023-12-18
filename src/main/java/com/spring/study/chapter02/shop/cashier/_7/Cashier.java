package com.spring.study.chapter02.shop.cashier._7;

import com.spring.study.chapter02.shop.cart.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Locale;

//@Component
public class Cashier {

    @Autowired
    private MessageSource messageSource;

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public void checkout(ShoppingCart cart) {
        String alert = messageSource.getMessage("alert.inventory.checkout",
                new Object[]{cart.getItems(), new Date()}, Locale.US);

        System.out.println(alert);
    }
}
