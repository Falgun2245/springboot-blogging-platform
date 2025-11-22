package com.bloggingsite.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

@Entity
@Table(name = "user_post_table")
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_name" , nullable = false)
    private String image_name;

    @Column(name = "post_title" , nullable = false)
    private String title;

    @Column(name = "post_content" , nullable = false)
    private String post_content;

    @CreatedDate
    @Column(name = "post_create_time" , nullable = false , updatable = false)
    private LocalDateTime postCreateTime;

    @LastModifiedDate
    @Column(name = "post_update_time" , nullable = false)
    private LocalDateTime postUpdateTime;

    @JoinColumn(name = "user_id" , nullable = false) // foreign key
    @ManyToOne(fetch = FetchType.EAGER)
    private UserEntity userEntity;

    @JoinColumn(name = "category_id" , nullable = false) // it is a Foreign key in PostEntity Table.
    @ManyToOne(fetch = FetchType.EAGER)
    private CategoryEntity category;

    @OneToMany(mappedBy = "post")
    private List<CommentEntity> commentEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<LikeEntity> likeEntityList = new ArrayList<>();



    public PostEntity(Long id, String image_name, String post_title, String post_content,
                      LocalDateTime postCreateTime, LocalDateTime postUpdateTime , UserEntity user_id ,
                      CategoryEntity categoryId , List<LikeEntity> likeEntityList) {
        this.id = id;
        this.image_name = image_name;
        this.title = post_title;
        this.post_content = post_content;
        this.postCreateTime = postCreateTime;
        this.postUpdateTime = postUpdateTime;
        this.userEntity = user_id;
        this.category = categoryId;
        this.likeEntityList = likeEntityList;
    }

    public PostEntity() {

        out.println("I am a Default Constructor From PostEntity...");
    }


    public void setCategory(CategoryEntity category) {

        this.category = category;
    }

    public CategoryEntity getCategory() {

        return  category;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
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
        return title;
    }

    public void setPost_title(String post_title) {
        this.title = post_title;
    }

    public String getPost_content() {
        return post_content;
    }

    public void setPost_content(String post_content) {
        this.post_content = post_content;
    }

    public LocalDateTime getPostCreateTime() {
        return postCreateTime;
    }

    public void setPostCreateTime(LocalDateTime postCreateTime) {
        this.postCreateTime = postCreateTime;
    }

    public LocalDateTime getPostUpdateTime() {
        return postUpdateTime;
    }

    public void setPostUpdateTime(LocalDateTime postUpdateTime) {
        this.postUpdateTime = postUpdateTime;
    }

    public List<LikeEntity> getLikeEntityList() {
        return likeEntityList;
    }

    public void setLikeEntityList(List<LikeEntity> likeEntityList) {
        this.likeEntityList = likeEntityList;
    }
}