package com.bloggingsite.entity;


import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

@Entity
@Table(name = "user_table")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name" , nullable = false)
    private String name;

    @Column(name = "user_email" , nullable = false , unique = true)
    private String email;

    @Column(name = "user_password" , nullable = false , unique = true)
    private String password;

    @Column(name = "user_about" , nullable = false)
    private String about;

    @Column(name = "user_image")
    private String userImage;

    @CreatedDate
    @Column(name = "user_create_time" , nullable = false , updatable = false)
    private LocalDateTime createDateTime;

    @LastModifiedDate
    @Column(name = "user_update_time" , nullable = false , updatable = true)
    private LocalDateTime updateDateTime;

    @OneToMany(mappedBy = "userEntity")
    private List<PostEntity> postEntity = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<CommentEntity> commentEntities = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<LikeEntity> likeEntityList = new ArrayList<>();


    public UserEntity(Long id, String name, String email, String password, String about ,
                      List<PostEntity> post , List<CommentEntity> commentEntities ,
                      List<LikeEntity> likeEntityList) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.about = about;
        this.postEntity = post;
        this.commentEntities = commentEntities;
        this.likeEntityList = likeEntityList;
    }

    public UserEntity() {

        out.println("I am Default UserEntity constructor.");
    }

    public List<PostEntity> getPostEntity() {
        return postEntity;
    }

    public void setPostEntity(List<PostEntity> postEntity) {
        this.postEntity = postEntity;
    }

    public LocalDateTime getUpdateDateTime(LocalDateTime now) {
        return updateDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
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

    public List<CommentEntity> getCommentEntities() {
        return commentEntities;
    }

    public void setCommentEntities(List<CommentEntity> commentEntities) {
        this.commentEntities = commentEntities;
    }

    public List<LikeEntity> getLikeEntityList() {
        return likeEntityList;
    }

    public void setLikeEntityList(List<LikeEntity> likeEntityList) {
        this.likeEntityList = likeEntityList;
    }
}