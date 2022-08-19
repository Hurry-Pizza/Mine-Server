package com.hurrypizza.digda.api.v1;

public class DefaultResponse {

    private Object data;

    public DefaultResponse(Object data) {
        this.data = data;
    }

    public static DefaultResponse create(Object data) {
        return new DefaultResponse(data);
    }

}
