package com.hurrypizza.digda.domain.user;

public interface UserAuthService {

    User validateUserByEmailAndPassword(final String email, final String password);

    void save(final String email, final String password, final String color);

}
