package com.bloggingsite.controller;

import com.bloggingsite.DTO.PostDto;
import com.bloggingsite.DTO.UserDto;
import com.bloggingsite.custom_exception.ResourceNotFoundException;
import com.bloggingsite.entity.PostEntity;
import com.bloggingsite.paylot.PostResponse;
import com.bloggingsite.service.ImageService;
import com.bloggingsite.service.PostService;
import com.bloggingsite.util.AppConstant;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
@RequestMapping("/api/v2/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private ImageService imageService;

    @Value("{PostImage.path}")
    private String path;

    @PostMapping("/create/user/{myId}/category/{Id}")
    public ResponseEntity<PostDto> createPost(@Valid @RequestParam(value = "image_name") MultipartFile[] multipartFile ,
                                              @RequestParam(value = "title") String imageTitle ,
                                              @RequestParam(value = "post_content") String postContent
            , @PathVariable  Long myId , @PathVariable Long Id) throws ResourceNotFoundException, IOException {

        PostDto post = new PostDto();
        post.setPost_title(imageTitle);
        post.setPost_content(postContent);

        return ResponseEntity.status(HttpStatus.CREATED).body(postService.createUserPost(post , myId , Id , multipartFile , path));
    }

    @GetMapping("/get/categoryName/{name}")
    public ResponseEntity<List<PostDto>> getCategoryByName(@PathVariable("name") String categoryName) {

        return ResponseEntity.status(HttpStatus.OK).body(postService.getCategoryByName(categoryName));
    }

    @GetMapping("/getAll/pageNo/{no}/pageSize/{size}")
    public ResponseEntity<PostResponse> getAllPost(@PathVariable("no") int pageNo , @PathVariable("size") int pageSize , @RequestParam(value = "sortBy" , defaultValue = "title" , required = false) String sortBy
                                                    , @RequestParam(value = "orderBy" , defaultValue = "dsc" , required = false) String orderBy) {

        return new ResponseEntity<PostResponse>( postService.getAllPostByPage(pageNo , pageSize , sortBy , orderBy), HttpStatus.OK);
    }

    @GetMapping("/id{Id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("Id") Long myId) throws ResourceNotFoundException {

        return ResponseEntity.status(HttpStatus.OK).body(postService.getUserPostById(myId));
    }

    @GetMapping("/name/{keyword}")
    public ResponseEntity<List<PostDto>> getPostTitleByName(@PathVariable("keyword") String postTitle) {

        return new ResponseEntity<List<PostDto>>(postService.getPostByTitle(postTitle) , HttpStatus.FOUND);
    }

    @PutMapping("/id{myId}")
    public ResponseEntity<PostDto> updateById(@PathVariable("myId") Long Id , @RequestBody @Valid PostDto postDto) throws ResourceNotFoundException{

        return ResponseEntity.status(HttpStatus.OK).body(postService.updateUserPostById(Id , postDto));
    }

    @DeleteMapping("/id{myId}")
    public ResponseEntity<String> deleteById(@PathVariable("myId") Long Id) {

        postService.deleteUserPostById(Id);

        return ResponseEntity.ok("User Post has Deleted Successfully with ID : " + Id);
    }
}