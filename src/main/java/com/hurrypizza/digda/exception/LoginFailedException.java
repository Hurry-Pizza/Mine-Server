package com.hurrypizza.digda.exception;

public class LoginFailedException extends RuntimeException {

    public LoginFailedException() {
        super("Login is failed.");
    }

    public LoginFailedException(String message) {
        super(message);
    }
}
