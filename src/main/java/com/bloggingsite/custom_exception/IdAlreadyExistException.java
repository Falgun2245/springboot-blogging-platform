package com.bloggingsite.custom_exception;

public class IdAlreadyExistException extends RuntimeException {

    private Long id;
    private String message;

    public IdAlreadyExistException(Long id , String message) {

        super(String.format("User ID %d : %s" , id , message)); // User ID 10 : Already Exist.
        this.id = id;
        this.message = message;
    }
}