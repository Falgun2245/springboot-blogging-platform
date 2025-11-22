package com.bloggingsite.custom_exception;

public class ResourceNotFoundByStr extends RuntimeException{

    private String message;
    private String resource;
    private String value;

    public ResourceNotFoundByStr(String message , String resource , String value) {

        super(String.format("%s not found with %s= %s",message , resource , value));
        this.message = message;
        this.resource = resource;
        this.value = value;
    }
}