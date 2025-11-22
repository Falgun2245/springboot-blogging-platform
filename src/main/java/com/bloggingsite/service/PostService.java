package com.bloggingsite.service;

import com.bloggingsite.DTO.PostDto;
import com.bloggingsite.custom_exception.ResourceNotFoundException;
import com.bloggingsite.paylot.PostResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PostService {

    PostDto createUserPost(PostDto postDto , Long myId , Long Id , MultipartFile[] multipartFile , String path) throws ResourceNotFoundException, IOException;

    List<PostDto> getAllUserPost(Integer  pageSize , Integer pageNo);

    PostDto getUserPostById(Long myId) throws  ResourceNotFoundException;

    PostDto updateUserPostById(Long id, PostDto postDto) throws ResourceNotFoundException;

    void deleteUserPostById(Long id);

    List<PostDto> getPostByTitle(String postTitle);

    PostResponse getAllPostByPage(int pageNo, int pageSize , String sortBy , String orderBy);

    List<PostDto> getCategoryByName(String categoryName);
}