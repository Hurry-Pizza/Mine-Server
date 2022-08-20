package com.hurrypizza.digda.config.security.common.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hurrypizza.digda.api.ApiResponse;
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
import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final TokenProvider provider;
    private final HttpAuthTokenSupport tokenSupport;
    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {
        var principal = (UserPrincipal) authentication.getPrincipal();
        var authToken = principal.getUserInfo();
        var successToken = provider.createToken(authToken);

        log.debug("User login succeed. Id: {}, Email: {}, Token: {}", authToken.getId(), authToken.getEmail(), successToken);
        tokenSupport.injectToken(httpServletResponse, successToken);
        httpServletResponse.setStatus(HttpStatus.OK.value());
        var emptyResponse = objectMapper.writeValueAsString(ApiResponse.emptyResponse());
        httpServletResponse.getWriter().write(emptyResponse);
    }

}
