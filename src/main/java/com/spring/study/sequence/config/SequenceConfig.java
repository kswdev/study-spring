package com.spring.study.sequence.config;

import com.spring.study.sequence.dao.SequenceDao;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@ComponentScan(
        basePackages = "com.spring.study.sequence",
        includeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.REGEX,
                        pattern = {"com.spring.study.sequence.dao.*DaoImpl",
                                   "com.spring.study.sequence.service.*Service"}
                )
        },
        excludeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ANNOTATION,
                        classes = {org.springframework.stereotype.Controller.class}
                )
        }
)
@Configuration
public class SequenceConfig {

    private final SequenceDao sequenceDao;
    public SequenceConfig(SequenceDao sequenceDao) {
        this.sequenceDao = sequenceDao;
        System.out.println(sequenceDao.getNextValue("IT"));
    }
}
