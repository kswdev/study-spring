package com.spring.study;

import com.spring.study.sequence.config.SequenceGeneratorConfiguration;
import com.spring.study.sequence.pojo.SequenceGenerator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(SequenceGeneratorConfiguration.class);

        SequenceGenerator generator = context.getBean("sequenceGenerator", SequenceGenerator.class);
        SequenceGenerator generator2 = (SequenceGenerator) context.getBean("sequenceGenerator");

        System.out.println(generator.getSequence());
        System.out.println(generator2.getSequence());
    }
}
