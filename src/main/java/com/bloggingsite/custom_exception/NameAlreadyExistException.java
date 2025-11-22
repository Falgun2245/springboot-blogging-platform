package com.bloggingsite.custom_exception;

public class NameAlreadyExistException extends RuntimeException{

    String message;

    public NameAlreadyExistException(String message) {

        super(String.format("%s",message));
        this.message = message;
    }
}