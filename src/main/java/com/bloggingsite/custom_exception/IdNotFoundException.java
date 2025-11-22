package com.bloggingsite.custom_exception;

public class IdNotFoundException extends RuntimeException{

    private Long userId;
    private Long postId;
    private String message1;
    private String message2;

    public IdNotFoundException(Long userId , Long postId , String message1 , String message2) {

        super(String.format("user ID : %d %s and post ID : %d %s" , userId , postId , message1 , message2));
        this.userId = userId;
        this.postId = postId;
        this.message1 = message1;
        this.message2 = message2;
    }
}
