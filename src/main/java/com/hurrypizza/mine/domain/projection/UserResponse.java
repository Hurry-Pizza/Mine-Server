package com.hurrypizza.mine.domain.projection;

import com.hurrypizza.mine.domain.user.User;
import com.hurrypizza.mine.domain.user.UserRole;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserResponse {

    private long id;
    private String email;
    private String password;
    private String color;
    private UserRole role;
    private String nickname;

    public static UserResponse from(User user) {
        return new UserResponse(user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getColor(),
                user.getRole(),
                user.getNickname());
    }

}
