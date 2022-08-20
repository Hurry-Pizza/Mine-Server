package com.hurrypizza.digda.domain.user;

import com.hurrypizza.digda.config.security.UserInfo;

public interface UserAuthService {

    User validateUserByEmailAndPassword(final String email, final String password);

    UserInfo save(final String email, final String password, final String color);

}
