package com.hurrypizza.mine.config.security.token.jwt;

import com.hurrypizza.mine.config.security.token.HttpAuthTokenSupport;
import com.hurrypizza.mine.config.security.token.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtTokenPersistFilter extends OncePerRequestFilter {

    private final HttpAuthTokenSupport httpTokenExtractor;
    private final TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        try {
            var tokenStr = httpTokenExtractor.extractToken(request);
            var authToken = tokenProvider.parseUserInfoFromToken(tokenStr);
            SecurityContextHolder.getContext().setAuthentication(new JwtAuthentication(authToken));
        } catch (Exception e) {
            log.debug("JwtTokenPersistFilter error", e);
            SecurityContextHolder.clearContext();
        }
        filterChain.doFilter(request, response);
    }

}
