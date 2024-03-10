package com.spring.study.chapter07.domain.user.entity;

import com.spring.study.chapter07.domain.user.converter.UserRoleConverter;
import com.spring.study.chapter07.domain.user.dto.sercurity.RoleType;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@SequenceGenerator(
        name = "USER_SEQ_GENERATOR",
        sequenceName = "USER_SEQ"
)
@ToString
@NoArgsConstructor
@Entity
@Table(name = "USER2")
@Getter @Setter
public class User {

    @Id @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "USER_SEQ_GENERATOR"
    )
    private Long id;

    @Column(unique = true)
    private String userName;
    private String password;

    @Convert(converter = UserRoleConverter.class)
    private Set<RoleType> roleTypes;

    @Builder
    public User(Long id, String userName, String password, Set<RoleType> roleTypes) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.roleTypes = roleTypes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(getId(), user.getId()) && Objects.equals(getUserName(), user.getUserName()) && Objects.equals(getPassword(), user.getPassword()) && getRoleTypes() == user.getRoleTypes();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserName(), getPassword(), getRoleTypes());
    }
}
