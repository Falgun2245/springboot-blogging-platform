package com.bloggingsite.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "commentTable")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "commentContent")
    private String commentContent;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private UserEntity user;

    @JoinColumn(name = "post_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private PostEntity post;

    public CommentEntity() {

        System.out.println("I am default Comment Entity Constructor...");
    }

    public CommentEntity(Long id, String content , UserEntity user , PostEntity post) {
        this.id = id;
        this.commentContent = content;
        this.user = user;
        this.post = post;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return commentContent;
    }

    public void setContent(String content) {
        this.commentContent = content;
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
}