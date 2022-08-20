package com.hurrypizza.mine.domain.user;

import com.hurrypizza.mine.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;

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
@SQLDelete(sql = "UPDATE user SET is_deleted = true WHERE user_id=?")
public class User extends BaseTimeEntity {

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

    @Column
    private String nickname;

    private User(String email,
                 String password,
                 String color,
                 UserRole role,
                 String nickname) {
        this.color = color;
        this.email = email;
        this.password = password;
        this.role = role;
        this.nickname = nickname;
    }

    public static User createNormalUser(final String email,
                                        final String password,
                                        final String color,
                                        final String nickname) {
        return new User(email, password, color, UserRole.ROLE_USER, nickname);
    }

}
