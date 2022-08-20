package com.hurrypizza.mine.domain.user;

import com.hurrypizza.mine.domain.projection.UserResponse;

import java.util.List;

public interface UserService {

    List<UserResponse> getAllUsers();

}
