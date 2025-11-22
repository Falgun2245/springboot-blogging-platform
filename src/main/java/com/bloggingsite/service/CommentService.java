package com.bloggingsite.service;

import com.bloggingsite.DTO.CommentDto;
import com.bloggingsite.custom_exception.ResourceNotFoundException;

import java.util.List;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto , Long myId , Long Id) throws ResourceNotFoundException;

    List<CommentDto> getAllComments();

    CommentDto getCommentById(Long myId);

    CommentDto updateCommentById(Long myId , CommentDto commentDto);

    void deleteCommentById(Long Id);

    List<CommentDto> getCommentByPage(int pageNo, int pageSize , String orderBy , String sortDir);
}