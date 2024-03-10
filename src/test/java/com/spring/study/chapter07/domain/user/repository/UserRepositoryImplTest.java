package com.spring.study.chapter07.domain.user.repository;

import com.spring.study.chapter07.domain.user.dto.sercurity.RoleType;
import com.spring.study.chapter07.domain.user.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserRepositoryImplTest {

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private UserRepositoryImpl userRepository;
    private EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction tx;

    @BeforeEach
    void init() {

        emf = Persistence.createEntityManagerFactory("hello");
        em  = emf.createEntityManager();
        tx  = em.getTransaction();

        userRepository = new UserRepositoryImpl();
        userRepository.setEntityManager(em);

        tx.begin();

        User user = User.builder()
                .userName("admin")
                .password(passwordEncoder.encode("admin"))
                .roleTypes(Set.of(RoleType.ADMIN, RoleType.USER))
                .build();

        userRepository.save(user);
    }
    @DisplayName("[repository - user] CURD TEST")
    @Test
    void crudTest() {

        User user = User.builder()
                .id(1L)
                .userName("admin")
                .password(passwordEncoder.encode("admin"))
                .roleTypes(Set.of(RoleType.ADMIN, RoleType.USER))
                .build();

        User admin = userRepository.findByName("admin");
        assertEquals(admin.getId(), user.getId());
    }

    @AfterEach
    void close() {
        tx.commit();
        em.close();
        emf.close();
    }
}