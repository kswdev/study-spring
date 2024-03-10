package com.spring.study.chapter07.domain.user.service;

import com.spring.study.chapter07.domain.user.dto.sercurity.RoleType;
import com.spring.study.chapter07.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        com.spring.study.chapter07.domain.user.entity.User user = userRepository.findByName(username);

        return User.builder()
                .username(username)
                .password(user.getPassword())
                .authorities(
                        user.getRoleTypes().stream()
                                .map(RoleType::getName)
                                .map(SimpleGrantedAuthority::new)
                                .collect(Collectors.toUnmodifiableSet())
                )
                .build();
    }
}
