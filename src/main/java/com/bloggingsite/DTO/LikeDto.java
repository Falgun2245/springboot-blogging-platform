package com.bloggingsite.DTO;

public class LikeDto {

    private Long id;

    private UserDto user;

    private PostDto post;

    public LikeDto() {

        System.out.println("I am a Default Constructor to create bean of this class by Hibernate");
    }

    public LikeDto(Long id , UserDto userDto , PostDto postDto) {

        this.id = id;
        this.post = postDto;
        this.user = userDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public PostDto getPost() {
        return post;
    }

    public void setPost(PostDto post) {
        this.post = post;
    }
}