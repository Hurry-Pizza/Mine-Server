package com.hurrypizza.digda.config.security.common.handler;

import com.hurrypizza.digda.config.security.common.UserPrincipal;
import com.hurrypizza.digda.config.security.token.HttpAuthTokenSupport;
import com.hurrypizza.digda.config.security.token.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final TokenProvider provider;
    private final HttpAuthTokenSupport tokenSupport;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) {
        var principal = (UserPrincipal) authentication.getPrincipal();
        var authToken = principal.getUserInfo();
        var successToken = provider.createToken(authToken);

        log.debug("User login succeed. Id: {}, Email: {}, Token: {}", authToken.getId(), authToken.getEmail(), successToken);
        tokenSupport.injectToken(httpServletResponse, successToken);
        httpServletResponse.setStatus(HttpStatus.OK.value());
    }

}
