package com.hurrypizza.digda.exception;

import org.springframework.security.core.AuthenticationException;

public class LoginFailedException extends AuthenticationException {

    public LoginFailedException() {
        super("Login is failed.");
    }

    public LoginFailedException(String message) {
        super(message);
    }
}
