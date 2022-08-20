package com.hurrypizza.digda.config.security.token;

import com.hurrypizza.digda.config.security.UserInfo;

public interface TokenProvider {
    String createToken(UserInfo token);

    UserInfo parseUserInfoFromToken(String tokenStr);
}
