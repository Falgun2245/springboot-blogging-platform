package com.bloggingsite.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public class CategoryDto {


    private Long id;

    @NotBlank(message = "category name is required!!!")
    @Pattern(regexp = "^[A-Za-z0-9\\s,.!?()'-]{3,50}$",
            message = "Category name must be 3–50 characters and contain only letters, numbers, spaces, or basic punctuation.")
    private String categoryName;


    @NotBlank(message = "category description is required")
    @Pattern(regexp = "^[A-Za-z0-9\\s.,!?()\"'@#&%$\\-_:;]{10,300}$",
            message = "Category description must be 10–300 characters and contain only valid text or punctuation.")
    private String categoryDescription;

//    @Pattern(regexp = "^.*\\\\.(jpg|jpeg|png|gif|webp)$" , message = "image name must be ends with jpg|jpeg|png|gif|webp")
    @NotBlank(message = "category image is required!!!")
    private String categoryImage;

    private List<PostDto> postDto;


    public CategoryDto() {

        System.out.println("I am Default Constructor of the Category DTO Class...");
    }

    public CategoryDto(Long id , String categoryName , String categoryDescription , String categoryImage , List<PostDto> postDto) {

        this.id = id;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.categoryImage = categoryImage;
        this.postDto = postDto;
    }

    public List<PostDto> getPostDto() {
        return postDto;
    }

    public void setPostDto(List<PostDto> postDto) {
        this.postDto = postDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }
}