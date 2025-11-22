package com.bloggingsite.controller;

import com.bloggingsite.custom_exception.ResourceNotFoundException;
import com.bloggingsite.service.LikeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(name = "api/v5/likes")
public class LikeController {

    Logger logger = LoggerFactory.getLogger(LikeController.class);

    @Autowired
    private LikeService likeService;

    @PostMapping("/userId/{UId}/postId/{PId}")
    public ResponseEntity<String> createLike(@PathVariable(name = "Id") Long userId,
                                             @PathVariable(name = "Id") Long postId) throws ResourceNotFoundException {

        likeService.createLike(userId , postId);
        return ResponseEntity.status(HttpStatus.CREATED).body("Like successfully");
    }
}