package com.hurrypizza.mine.config.security.token;

import com.hurrypizza.mine.config.security.UserInfo;

public interface TokenProvider {
    String createToken(UserInfo token);

    UserInfo parseUserInfoFromToken(String tokenStr);
}
