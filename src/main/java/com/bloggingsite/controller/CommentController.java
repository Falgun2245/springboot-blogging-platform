package com.bloggingsite.controller;

import com.bloggingsite.DTO.CommentDto;
import com.bloggingsite.custom_exception.ResourceNotFoundException;
import com.bloggingsite.service.CommentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v4/comments")
public class CommentController {

    Logger logger = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    private CommentService commentService;


    @PostMapping("/create/user/{Id}/post/{myId}")
    public ResponseEntity<CommentDto> createComment(@Valid @RequestBody CommentDto commentDto ,
                                                    @PathVariable("Id") Long myId ,
                                                    @PathVariable("myId") Long Id) throws ResourceNotFoundException {

        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createComment(commentDto , myId , Id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CommentDto>> getAllComment() {

        return new ResponseEntity<List<CommentDto>>(commentService.getAllComments() , HttpStatus.OK);
    }


    @GetMapping("/id/{Id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable("Id") Long myId) {

        return ResponseEntity.status(HttpStatus.OK).body(commentService.getCommentById(myId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<CommentDto>> getCommentsByPages(@RequestParam(value = "no" , required = false) int pageNo ,
                                                               @RequestParam(value = "size" , required = false) int pageSize ,
                                                               @RequestParam(value = "orderBy" , required = false) String orderBy ,
                                                               @RequestParam(value = "direction" , required = false) String sortDir) {

        return ResponseEntity.status(HttpStatus.OK).body(commentService.getCommentByPage(pageNo , pageSize , orderBy , sortDir));

    }

    @PutMapping("/id/{Id}")
    public ResponseEntity<CommentDto> modifyCommentBYId(@PathVariable("Id") Long myId , @Valid @RequestBody CommentDto commentDto) {

        return new ResponseEntity<>(commentService.updateCommentById(myId , commentDto) , HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/id/{Id}")
    public ResponseEntity<String> deleteCommentById(@PathVariable("Id") Long myId) {

        commentService.deleteCommentById(myId);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("comment has deleted successfully");
    }
}