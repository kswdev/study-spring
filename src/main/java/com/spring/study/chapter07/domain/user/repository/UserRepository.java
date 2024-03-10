package com.spring.study.chapter07.domain.user.repository;

import com.spring.study.chapter07.domain.user.entity.User;

public interface UserRepository {

    User findByName(String userName);
    User save(User user);
}
