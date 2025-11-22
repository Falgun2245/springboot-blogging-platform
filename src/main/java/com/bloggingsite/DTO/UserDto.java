package com.bloggingsite.DTO;

import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class UserDto {

    private Long id;

    @Pattern(regexp = "^[a-zA-Z\\s]+$" , message = "Name can content only letters and space")
    @NotBlank(message = "name is required!!!")
    @Size(min = 3 , max = 12 , message = "name allow min 3 characters")
    private String name; // Kadiya Falgun

    @NotBlank(message = "email is required!!!")
    @Email(message = "email is not valid")
    private String email;

    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).*$",
            message = "you can enter lower case & upper case, number, special characters"
    )    @NotBlank(message = "password is required!!!")
    @Size(min = 8 , message = "min password size is 8")
    private String password;


    private String userImage;

    @Pattern(regexp = "^[a-zA-Z\\s]+$" , message = "About can content only letters and space")
    @NotBlank(message = "about is required!!!")
    @Size(min = 30 , message = "you can write about text up to 30 and more than 30 characters")
    private String about;


//    private List<PostDto> post = new ArrayList<>();

    public UserDto(Long id, String name, String email, String password, String about , String userImage) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.about = about;
        this.userImage = userImage;
    }

    public UserDto() {

        out.println();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }


    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }
}
