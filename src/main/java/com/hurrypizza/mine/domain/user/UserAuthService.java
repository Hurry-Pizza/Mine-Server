package com.hurrypizza.mine.domain.user;

import com.hurrypizza.mine.config.security.UserInfo;

public interface UserAuthService {

    User validateUserByEmailAndPassword(final String email, final String password);

    UserInfo save(final String email, final String password, final String color, final String nickname);

}
