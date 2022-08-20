package com.hurrypizza.mine.exception;

public class ResourceNotExistException extends RuntimeException {

    public ResourceNotExistException() {
        super("Resource not existed");
    }

}
