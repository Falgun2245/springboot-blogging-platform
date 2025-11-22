package com.bloggingsite.custom_exception;

public class ResourceNotFoundException extends Exception{

    private String filedName;

    private String resource;

    private Long filedValue;

    public ResourceNotFoundException(String filedName, String resource, Long filedValue) {

        super(String.format("%s with %s=%d not found", resource, filedName, filedValue));
        this.filedName = filedName;
        this.resource = resource; // id with user=15 not found
        this.filedValue = filedValue;
    }
}