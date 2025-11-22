package com.bloggingsite.custom_exception;

public class NotExistException extends RuntimeException {

    private String message;

    public NotExistException(String message) {

        super(String.format("%s" , message));
        this.message = message;
    }
}