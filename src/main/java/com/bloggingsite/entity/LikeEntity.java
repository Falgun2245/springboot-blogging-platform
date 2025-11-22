package com.bloggingsite.entity;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "LikeTable")
public class LikeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(name = "createTime" , nullable = false)
    private LocalDateTime likeCreateTime;

    @CreatedDate
    @Column(name = "updateTime" , nullable = false)
    private LocalDateTime likeUpdateTime;

    @Column(name = "Active" , nullable = false)
    private boolean active = true;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private UserEntity user;

    @JoinColumn(name = "post_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private PostEntity post;

    public LikeEntity() {

        System.out.println("I am called by Hibernate...");
    }

    public LikeEntity(Long id , LocalDateTime likeCreateTime,
                      LocalDateTime likeUpdateTime , UserEntity user,
                      PostEntity post , boolean active) {

        this.id = id;
        this.user = user;
        this.post = post;
        this.active = active;
        this.likeCreateTime = likeCreateTime;
        this.likeUpdateTime = likeUpdateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getLikeCreateTime() {
        return likeCreateTime;
    }

    public void setLikeCreateTime(LocalDateTime likeCreateTime) {
        this.likeCreateTime = likeCreateTime;
    }

    public LocalDateTime getLikeUpdateTime() {
        return likeUpdateTime;
    }

    public void setLikeUpdateTime(LocalDateTime likeUpdateTime) {
        this.likeUpdateTime = likeUpdateTime;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public PostEntity getPost() {
        return post;
    }

    public void setPost(PostEntity post) {
        this.post = post;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
