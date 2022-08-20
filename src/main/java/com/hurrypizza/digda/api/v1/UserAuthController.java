package com.hurrypizza.digda.api.v1;

import com.hurrypizza.digda.api.v1.dto.UserRegisterRequest;
import com.hurrypizza.digda.domain.user.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users/auth")
@RequiredArgsConstructor
public class UserAuthController {

    private final UserAuthService userAuthService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Validated UserRegisterRequest request) {
        var encodedPassword = passwordEncoder.encode(request.getPassword());
        userAuthService.save(request.getEmail(), encodedPassword, request.getColor());
    }

}
