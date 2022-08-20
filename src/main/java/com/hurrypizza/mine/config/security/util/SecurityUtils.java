package com.hurrypizza.mine.config.security.util;

import com.hurrypizza.mine.config.security.UserInfo;
import com.hurrypizza.mine.config.security.token.jwt.JwtAuthentication;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.SecureRandom;

public class SecurityUtils {

    private static final String NUMBER_ALPHABET_STR = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final SecureRandom RANDOM = new SecureRandom();

    private SecurityUtils() {
    }

    public static UserInfo getCurrentUserInfo() {
        try {
            var authentication = (JwtAuthentication) SecurityContextHolder.getContext()
                                                             .getAuthentication();
            return authentication.getUserInfo();
        } catch (Exception e) {
            throw new AccessDeniedException("유저 인증 정보가 존재하지 않습니다");
        }
    }

    public static boolean isAuthenticated() {
        var context = SecurityContextHolder.getContext()
                              .getAuthentication();
        return context instanceof JwtAuthentication;
    }

    public static String generateRandomString(int len) {
        var sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(NUMBER_ALPHABET_STR.charAt(RANDOM.nextInt(NUMBER_ALPHABET_STR.length())));
        }
        return sb.toString();
    }

}
