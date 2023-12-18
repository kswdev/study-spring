package com.spring.study.chapter02.shop.config;

import com.spring.study.chapter02.shop.cashier.aware.Cashier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;

@Configuration
@Profile("global")
@ComponentScan("com.spring.study.chapter02.shop")
public class ShopConfigurationGlobal {

    @Value("classpath:banner.txt")
    private Resource banner;

    @Bean(initMethod = "openFile", destroyMethod = "closeFile")
    public Cashier cashier() {
        final String path = System.getProperty("java.io.tmpdir") + "cashier";
        Cashier c1 = new Cashier();
        c1.setPath(path);
        return c1;
    }
}