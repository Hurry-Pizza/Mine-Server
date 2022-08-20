package com.hurrypizza.digda.config.security.authn;

import com.hurrypizza.digda.config.security.UserInfo;
import com.hurrypizza.digda.config.security.common.UserPrincipal;
import com.hurrypizza.digda.api.v1.dto.UserLoginRequest;
import com.hurrypizza.digda.domain.user.UserAuthService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.Collections;

public class UserNamePasswordAuthenticationProvider implements AuthenticationProvider {

    private final UserAuthService userAuthService;

    public UserNamePasswordAuthenticationProvider(UserAuthService userAuthService) {
        this.userAuthService = userAuthService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) {
        var passwordAuthenticationToken = (UsernamePasswordAuthenticationToken) authentication;
        var command = makeLoginCommandFormToken(passwordAuthenticationToken);
        var userInfo = tryLoginOrThrow(command);
        return getCompleteAuthToken(userInfo, command.getPassword());
    }

    private UserInfo tryLoginOrThrow(UserLoginRequest command) {
        var user = userAuthService.validateUserByEmailAndPassword(command.getEmail(), command.getPassword());
        return UserInfo.from(user);
    }

    private UserLoginRequest makeLoginCommandFormToken(UsernamePasswordAuthenticationToken token) {
        var username = token.getName();
        var password = (String) token.getCredentials();
        return new UserLoginRequest(username, password);
    }

    private UsernamePasswordAuthenticationToken getCompleteAuthToken(UserInfo loginUser, String credential) {
        var principal = UserPrincipal
                                          .builder()
                                          .attributes(Collections.singletonMap("password", credential))
                                          .userInfo(loginUser)
                                          .build();
        return new UsernamePasswordAuthenticationToken(principal, credential, principal.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }

}
