package com.hurrypizza.digda.domain.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String color;

    @Enumerated(EnumType.STRING)
    @Column
    private UserRole role;

    private User(String email, String password, String color, UserRole role) {
        this.color = color;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public static User createNormalUser(String email, String password, String color) {
        return new User(email, password, color, UserRole.ROLE_USER);
    }

}
