package com.bloggingsite.custom_exception;

public class EmptyException extends RuntimeException {

    private String message;

    public EmptyException(String message) {

        super(String.format("%s" , message));
        this.message = message;
    }
}
