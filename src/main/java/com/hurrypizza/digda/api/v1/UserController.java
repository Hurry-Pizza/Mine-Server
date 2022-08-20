package com.hurrypizza.digda.api.v1;

import com.hurrypizza.digda.api.ApiResponse;
import com.hurrypizza.digda.domain.user.User;
import com.hurrypizza.digda.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping
    public ApiResponse<List<User>> users() {
        return ApiResponse.of(userRepository.findAll());
    }

}
