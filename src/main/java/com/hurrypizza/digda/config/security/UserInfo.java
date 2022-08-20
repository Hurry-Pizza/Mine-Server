package com.hurrypizza.digda.config.security;

import com.hurrypizza.digda.domain.user.User;
import com.hurrypizza.digda.domain.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    private long id;
    private String email;
    private UserRole role;

    public static UserInfo from(User user) {
        return new UserInfo(user.getId(), user.getEmail(), user.getRole());
    }

}
