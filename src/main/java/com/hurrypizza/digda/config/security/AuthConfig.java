package com.hurrypizza.digda.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hurrypizza.digda.config.security.authn.UserNamePasswordAuthenticationProvider;
import com.hurrypizza.digda.config.security.common.handler.LoginFailureHandler;
import com.hurrypizza.digda.config.security.common.handler.LoginSuccessHandler;
import com.hurrypizza.digda.config.security.token.HttpAuthTokenSupport;
import com.hurrypizza.digda.config.security.token.TokenProvider;
import com.hurrypizza.digda.config.security.token.jwt.JwtTokenProvider;
import com.hurrypizza.digda.config.security.token.jwt.JwtTokenSupport;
import com.hurrypizza.digda.domain.user.UserAuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AuthConfig {

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Bean
    public UserNamePasswordAuthenticationProvider directLoginProvider(UserAuthService userAuthService) {
        return new UserNamePasswordAuthenticationProvider(userAuthService);
    }

    @Bean
    public TokenProvider tokenProvider(ObjectMapper mapper) {
        return new JwtTokenProvider(secretKey, mapper);
    }

    @Bean
    public HttpAuthTokenSupport tokenSupport() {
        return new JwtTokenSupport();
    }

    @Bean
    public LoginSuccessHandler successHandler(TokenProvider tokenProvider,
                                              HttpAuthTokenSupport authTokenSupport) {
        return new LoginSuccessHandler(tokenProvider, authTokenSupport);
    }

    @Bean
    public LoginFailureHandler failureHandler() {
        return new LoginFailureHandler();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}
