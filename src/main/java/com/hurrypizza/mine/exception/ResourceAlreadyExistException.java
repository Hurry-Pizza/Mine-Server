package com.hurrypizza.mine.exception;

public class ResourceAlreadyExistException extends RuntimeException {

    public ResourceAlreadyExistException(String resourceInfo) {
        super("Resource already existed: Resource [%s]".formatted(resourceInfo));
    }

}
