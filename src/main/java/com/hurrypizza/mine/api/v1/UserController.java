package com.hurrypizza.mine.api.v1;

import com.hurrypizza.mine.api.ApiResponse;
import com.hurrypizza.mine.domain.projection.UserResponse;
import com.hurrypizza.mine.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ApiResponse<List<UserResponse>> users() {
        return ApiResponse.of(userService.getAllUsers());
    }

}
