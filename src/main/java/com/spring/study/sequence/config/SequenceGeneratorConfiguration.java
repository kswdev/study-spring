package com.spring.study.sequence.config;

import com.spring.study.sequence.pojo.Sequence;
import com.spring.study.sequence.pojo.SequenceGenerator;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestParam;

@Configuration
@Import(SequenceConfig.class)
public class SequenceGeneratorConfiguration {


    @Value("#{uniqueSequence}")
    private Sequence sequence;

    @Bean
    @DependsOn("datePrefixGenerator")
    public Sequence sequence() {
        sequence.setId(sequenceGenerator().getSequence());
        return sequence;
    }

    @Required
    @Bean
    SequenceGenerator sequenceGenerator() {
        SequenceGenerator seqgen = new SequenceGenerator();
        seqgen.setPrefix("30");
        seqgen.setSuffix("A");
        seqgen.setInitial(100000);

        return seqgen;
    }
}
