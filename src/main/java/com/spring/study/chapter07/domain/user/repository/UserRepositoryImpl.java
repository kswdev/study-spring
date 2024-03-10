package com.spring.study.chapter07.domain.user.repository;

import com.spring.study.chapter07.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository{

    @Setter
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public User findByName(String userName) {
        List<User> resultList = entityManager.createQuery("select u from User u where u.userName = :userName", User.class)
                .setParameter("userName", userName)
                .getResultList();

        if (resultList.isEmpty()) return new User();

        return resultList.get(0);
    }

    @Override
    public User save(User user) {
        entityManager.persist(user);
        return user;
    }
}
