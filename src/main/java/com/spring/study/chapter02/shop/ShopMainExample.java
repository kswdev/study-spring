package com.spring.study.chapter02.shop;

import com.spring.study.chapter02.shop.config.ShopConfiguration;
import com.spring.study.chapter02.shop.product.Product;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ShopMainExample {

    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(ShopConfiguration.class);

        Product aaa = context.getBean("aaa", Product.class);
        Product cdrw = context.getBean("cdrw", Product.class);

        System.out.println(aaa);
        System.out.println(cdrw);
    }
}
