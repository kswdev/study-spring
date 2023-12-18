package com.spring.study.chapter02.shop.config;

import com.spring.study.chapter02.shop.product.Battery;
import com.spring.study.chapter02.shop.product.Disc;
import com.spring.study.chapter02.shop.product.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"summer", "winter"})
@ComponentScan("com.spring.study.chapter02.shop")
public class ShopConfigurationSumWin {

    @Bean
    public Product aaa() {
        Battery p1 = new Battery();
        p1.setName("AAA");
        p1.setPrice(2.0);
        p1.setRechargeable(true);
        return p1;
    }

    @Bean
    public Product cdrw() {
        Disc p2 = new Disc("CD-RW", 1.0);
        p2.setCapacity(700);
        return p2;
    }

    @Bean
    public Product dvdrw() {
        Disc p2 = new Disc("DVD-RW", 2.5);
        p2.setCapacity(700);
        return p2;
    }
}