package com.spring.study.sequence.dao;

import com.spring.study.sequence.pojo.Sequence;

public interface SequenceDao {
    public Sequence getSequence(String sequenceId);
    public int getNextValue(String sequenceId);
}
