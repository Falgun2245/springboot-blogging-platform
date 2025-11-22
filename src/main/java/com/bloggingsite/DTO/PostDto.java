package com.bloggingsite.DTO;

import com.bloggingsite.entity.CategoryEntity;
import com.bloggingsite.entity.UserEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import static java.lang.System.out;

public class PostDto {

    private Long id;

//    @Pattern(regexp = "^[a-zA-Z0-9-_]+\\\\.(jpg|jpeg|png|gif)$"
//            , message = "Image name must be alphabets and ends with .jpg/jpeg/png/gif")
    @NotBlank(message = "image name is required!!!")
    private String image_name;

    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "post title have only letters and space")
    @Size(min = 5, message = "Post title must have min 5 characters")
    @NotBlank(message = "post title is required!!!")
    private String post_title;

    @Pattern(regexp = "^[a-zA-Z0-9\\s\\-,.?!'\":;()\\[\\]]*$"
            , message = "Post content can have only letters,numerical,space.")
    @Size(min = 10, max = 5000, message = "Post content must be between min=10 to max=5000 characters")
    @NotBlank(message = "Post content is required!!!")
    private String post_content;

    private UserDto userEntity;

    private CategoryDto category;


    public PostDto(Long id, String image_name, String post_title, String post_content , UserDto userEntity , CategoryDto categoryId) {

        this.id = id;
        this.image_name = image_name;
        this.post_title = post_title;
        this.post_content = post_content;
        this.userEntity = userEntity;
        this.category = categoryId;
    }

    public PostDto() {

        out.println("I am PostDto Constructor...");
    }

    public CategoryDto getCategory() {

        return category;
    }

    public void setCategory(CategoryDto category) {

        this.category = category;
    }

    public UserDto getUserEntity() {

        return userEntity;
    }

    public void setUserEntity(UserDto userEntity) {

        this.userEntity = userEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public String getPost_title() {
        return post_title;
    }

    public void setPost_title(String post_title) {
        this.post_title = post_title;
    }

    public String getPost_content() {
        return post_content;
    }

    public void setPost_content(String post_content) {
        this.post_content = post_content;
    }
}