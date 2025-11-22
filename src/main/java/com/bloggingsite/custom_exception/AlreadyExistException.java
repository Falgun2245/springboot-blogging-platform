package com.bloggingsite.custom_exception;

public class AlreadyExistException extends RuntimeException{

    private String message;
    private String value;

    public AlreadyExistException(String message) {

        super(String.format("%s" , message)); // Email is Already Exist!!!
        this.message = message;
        this.value = value;
    }
}