package com.bloggingsite.paylot;

import com.bloggingsite.DTO.PostDto;

import java.util.ArrayList;
import java.util.List;

public class PostResponse {

    private List<PostDto> content = new ArrayList<>();

    private int totalNumberOfPage;

    private Long totalNumberOfPost;

    private int currentNumberOfPage;

    private int currentNumberOfPost;

    private boolean lastNumberOfPage;

    public PostResponse(List<PostDto> content, int totalNumberOfPage, Long totalNumberOfPost, int currentNumberOfPage, int currentNumberOfPost, boolean lastNumberOfPage) {
        this.content = content;
        this.totalNumberOfPage = totalNumberOfPage;
        this.totalNumberOfPost = totalNumberOfPost;
        this.currentNumberOfPage = currentNumberOfPage;
        this.currentNumberOfPost = currentNumberOfPost;
        this.lastNumberOfPage = lastNumberOfPage;
    }

    public PostResponse() {

        System.out.println("I am Post Response Constructor....");
    }

    public List<PostDto> getContent() {
        return content;
    }

    public void setContent(List<PostDto> content) {
        this.content = content;
    }

    public int getTotalNumberOfPage() {
        return totalNumberOfPage;
    }

    public void setTotalNumberOfPage(int totalNumberOfPage) {
        this.totalNumberOfPage = totalNumberOfPage;
    }

    public Long getTotalNumberOfPost() {
        return totalNumberOfPost;
    }

    public void setTotalNumberOfPost(Long totalNumberOfPost) {
        this.totalNumberOfPost = totalNumberOfPost;
    }

    public int getCurrentNumberOfPage() {
        return currentNumberOfPage;
    }

    public void setCurrentNumberOfPage(int currentNumberOfPage) {
        this.currentNumberOfPage = currentNumberOfPage;
    }

    public int getCurrentNumberOfPost() {
        return currentNumberOfPost;
    }

    public void setCurrentNumberOfPost(int currentNumberOfPost) {
        this.currentNumberOfPost = currentNumberOfPost;
    }

    public boolean isLastNumberOfPage() {
        return lastNumberOfPage;
    }

    public void setLastNumberOfPage(boolean lastNumberOfPage) {
        this.lastNumberOfPage = lastNumberOfPage;
    }
}
