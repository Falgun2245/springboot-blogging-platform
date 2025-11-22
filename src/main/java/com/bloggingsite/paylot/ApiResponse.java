package com.bloggingsite.paylot;

public class ApiResponse {

    private String message;

    private  boolean response;

    public ApiResponse(String message, boolean response) {

        this.message = message;
        this.response = response;
    }

    public void setMessage(String message) {

        this.message = message;
    }

    public void setResponse(boolean response) {

        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public boolean isResponse() {
        return response;
    }
}
