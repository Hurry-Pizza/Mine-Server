package com.hurrypizza.digda.exception;

public class ResourceNotExistException extends RuntimeException {

    public ResourceNotExistException() {
        super("Resource not existed");
    }

}
