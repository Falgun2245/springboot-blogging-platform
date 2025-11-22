package com.bloggingsite.paylot;

public class UserPostApiResponse {

    private String message;

    private boolean resource;

    public UserPostApiResponse(String message , boolean resource) {

        this.message = message;
        this.resource = resource;
    }
}
