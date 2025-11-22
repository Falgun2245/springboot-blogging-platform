package com.bloggingsite.service;

import com.bloggingsite.DTO.LikeDto;
import com.bloggingsite.custom_exception.ResourceNotFoundException;
import com.bloggingsite.entity.LikeEntity;

import java.util.List;

public interface LikeService {

    LikeEntity createLike(Long userId , Long postId) throws ResourceNotFoundException;
}