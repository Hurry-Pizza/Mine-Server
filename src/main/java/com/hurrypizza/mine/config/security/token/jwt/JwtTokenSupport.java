package com.hurrypizza.mine.config.security.token.jwt;

import com.hurrypizza.mine.config.security.token.HttpAuthTokenSupport;
import com.hurrypizza.mine.exception.LoginFailedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class JwtTokenSupport implements HttpAuthTokenSupport {

    public static final String TOKEN_TYPE = "Bearer";

    public static String tokenWithType(String token) {
        return TOKEN_TYPE + " " + token;
    }

    @Override
    public String extractToken(HttpServletRequest target) {
        try {
            String tokenTypeAndStr = target.getHeader(HttpHeaders.AUTHORIZATION);
            log.debug("Parsing token in header: {}", tokenTypeAndStr);
            return tokenTypeAndStr.split(" ")[1];
        } catch (Exception e) {
            throw new LoginFailedException();
        }
    }

    @Override
    public void injectToken(HttpServletResponse dest, String token) {
        dest.addHeader(HttpHeaders.AUTHORIZATION, tokenWithType(token));
    }
}
