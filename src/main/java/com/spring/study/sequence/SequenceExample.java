package com.spring.study.sequence;

import com.spring.study.sequence.dao.SequenceDao;
import com.spring.study.sequence.service.SequenceService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SequenceExample {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext("com.spring.study.sequence.config");

        SequenceDao sequenceDao = context.getBean(SequenceDao.class);


        System.out.println(sequenceDao.getNextValue("IT"));
        System.out.println(sequenceDao.getNextValue("IT"));

        SequenceService service = context.getBean("sequenceService", SequenceService.class);
        System.out.println(service.generate("IT"));
    }
}
