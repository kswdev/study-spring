package com.spring.study.chapter02.shop.config.postProcessor;

import com.spring.study.chapter02.shop.product.Product;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class AuditCheckBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Product product) {
            String productName = product.getName();
            System.out.println("In AuditCheckBeanPostProcessor.postProcessBeforeInitialization," +
                    "processing Product: " + productName);
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
