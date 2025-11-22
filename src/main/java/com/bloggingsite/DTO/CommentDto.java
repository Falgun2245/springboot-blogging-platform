package com.bloggingsite.DTO;

import com.bloggingsite.entity.UserEntity;
import jakarta.validation.constraints.NotBlank;
import org.apache.catalina.User;

import static java.lang.System.out;

public class CommentDto {

    private Long id;

    @NotBlank
    private String commentContent;


    private UserDto user;


    private PostDto post;

    public CommentDto() {

        out.println("I am CommentDto Constructor...");
    }

    public CommentDto(Long id, String commentContent , UserDto user , PostDto post) {

        this.id = id;
        this.commentContent = commentContent;
        this.user = user;
        this.post = post;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public UserDto getUserDto() {
        return user;
    }

    public void setUserDto(UserDto user) {

        this.user = user;
    }

    public PostDto getPost() {
        return post;
    }

    public void setPost(PostDto post) {
        this.post = post;
    }
}