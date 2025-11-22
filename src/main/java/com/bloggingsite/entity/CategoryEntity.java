package com.bloggingsite.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;


@Entity
@Table(name = "categoryTable")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "categoryName" , nullable = false , unique = true)
    private String categoryName;

    @Column(name = "categoryDescription" , nullable = false)
    private String categoryDescription;

    @Column(name = "categoryImage" , nullable = false)
    private String categoryImage;

    @CreatedDate
    @Column(name = "createDate" , nullable = false , updatable = false)
    private LocalDateTime categoryCreateTime;

    @LastModifiedDate
    @Column(name = "updateDate" , nullable = false)
    private LocalDateTime categoryUpdateTime;

    @OneToMany(mappedBy = "category")
    private List<PostEntity> post = new ArrayList<>();

    public CategoryEntity() {

        out.println("I am Default CategoryEntity Constructor...");
    }

    public CategoryEntity(Long id , String categoryName , String categoryDescription , LocalDateTime categoryCreateTime , LocalDateTime categoryUpdateTime , String categoryImage , List<PostEntity> post) {

        this.id = id;
        this.categoryName = categoryName;
        this.categoryImage = categoryImage;
        this.categoryDescription = categoryDescription;
        this.categoryCreateTime = categoryCreateTime;
        this.categoryUpdateTime = categoryUpdateTime;
        this.post = post;
    }

    public List<PostEntity> getPost() {
        return post;
    }

    public void setPost(List<PostEntity> post) {
        this.post = post;
    }

    public LocalDateTime getCategoryCreateTime() {
        return categoryCreateTime;
    }

    public void setCategoryCreateTime(LocalDateTime categoryCreateTime) {
        this.categoryCreateTime = categoryCreateTime;
    }

    public LocalDateTime getCategoryUpdateTime() {
        return categoryUpdateTime;
    }

    public void setCategoryUpdateTime(LocalDateTime categoryUpdateTime) {
        this.categoryUpdateTime = categoryUpdateTime;
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

    public static void main(String[] args) {

        out.println("Hello My Heart JAVA How are You Hey I am Fine And You...");
    }
}