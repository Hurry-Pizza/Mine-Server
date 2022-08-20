package com.hurrypizza.mine.api.v1;

import com.hurrypizza.mine.api.ApiResponse;
import com.hurrypizza.mine.api.v1.dto.UserRegisterRequest;
import com.hurrypizza.mine.config.security.token.TokenProvider;
import com.hurrypizza.mine.config.security.token.jwt.JwtTokenSupport;
import com.hurrypizza.mine.config.security.util.SecurityUtils;
import com.hurrypizza.mine.domain.user.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
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
    private final TokenProvider tokenProvider;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiResponse<String>> save(@RequestBody @Validated UserRegisterRequest request) {
        var encodedPassword = passwordEncoder.encode(request.getPassword());
        var userInfo = userAuthService.save(request.getEmail(),
                encodedPassword,
                request.getColor(),
                request.getNickname());
        var token = tokenProvider.createToken(userInfo);
        return ResponseEntity.status(HttpStatus.CREATED)
                       .header(HttpHeaders.AUTHORIZATION, JwtTokenSupport.tokenWithType(token))
                       .body(ApiResponse.emptyResponse());
    }

    @GetMapping("/validate")
    public ResponseEntity<ApiResponse<String>> validateLogin() {
        if (SecurityUtils.isAuthenticated()) {
            return ResponseEntity.ok().body(ApiResponse.emptyResponse());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiResponse.emptyResponse());
    }

}
