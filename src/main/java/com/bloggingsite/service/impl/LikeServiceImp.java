package com.bloggingsite.service.impl;

import com.bloggingsite.custom_exception.*;
import com.bloggingsite.entity.LikeEntity;
import com.bloggingsite.entity.PostEntity;
import com.bloggingsite.entity.UserEntity;
import com.bloggingsite.repository.LikeRepository;
import com.bloggingsite.repository.PostRepository;
import com.bloggingsite.repository.UserRepository;
import com.bloggingsite.service.LikeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LikeServiceImp implements LikeService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LikeRepository likeRepository;

    @Override
    public  LikeEntity createLike(Long userId, Long postId) throws ResourceNotFoundException {

        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("id", "is not found", userId));
        PostEntity post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("id", "is not found", postId));

        LikeEntity likeEntity2 = null;

        likeEntity2.setUser(user);
        likeEntity2.setPost(post);
        likeEntity2.setLikeCreateTime(LocalDateTime.now());

        LikeEntity createdLike = likeRepository.save(likeEntity2);

        return createdLike;
    }
}