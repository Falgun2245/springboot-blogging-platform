package com.bloggingsite.service.impl;

import com.bloggingsite.DTO.CommentDto;
import com.bloggingsite.DTO.PostDto;
import com.bloggingsite.DTO.UserDto;
import com.bloggingsite.custom_exception.IdAlreadyExistException;
import com.bloggingsite.custom_exception.ResourceNotFoundException;
import com.bloggingsite.entity.CommentEntity;
import com.bloggingsite.entity.PostEntity;
import com.bloggingsite.entity.UserEntity;
import com.bloggingsite.paylot.PostResponse;
import com.bloggingsite.repository.CommentRepository;
import com.bloggingsite.repository.PostRepository;
import com.bloggingsite.repository.UserRepository;
import com.bloggingsite.service.CommentService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImp implements CommentService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PostRepository postRepository;


    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Long myId, Long Id) throws ResourceNotFoundException {

        UserEntity user = userRepository.findById(myId).orElseThrow(() -> new ResourceNotFoundException("id" , "user" , myId));

        PostEntity post = postRepository.findById(Id).orElseThrow(() -> new ResourceNotFoundException("id" , "post" , Id));

        CommentEntity commentEntity = modelMapper.map(commentDto , CommentEntity.class);

        commentEntity.setUser(user);
        commentEntity.setPost(post);

         CommentEntity createdComment = commentRepository.save(commentEntity);

         CommentDto comment = modelMapper.map(createdComment , CommentDto.class);

         comment.setUserDto(modelMapper.map(createdComment.getUser() , UserDto.class));

         return comment;
    }


    // Here is a problem to solve it Remaining...

    @Override
    public List<CommentDto> getAllComments() {

        List<CommentEntity> commentEntity = commentRepository.findAll();

        CommentEntity comment = (CommentEntity) commentEntity;

         return commentEntity.stream()
                .map(commentEntity1 -> modelMapper.map(commentEntity1 , CommentDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long myId) {

        CommentEntity commentEntity = commentRepository.findById(myId).orElseThrow(() -> new IdAlreadyExistException(myId , "is not found!!!"));

        CommentDto comment = modelMapper.map(commentEntity , CommentDto.class);

        return comment;
    }

    @Override
    public CommentDto updateCommentById(Long myId, CommentDto commentDto) {

        CommentEntity commentEntity = commentRepository.findById(myId).orElseThrow(() -> new IdAlreadyExistException(myId, " is not found!!!"));

        commentEntity.setContent(commentDto.getCommentContent());

        CommentEntity updatedComment = commentRepository.save(commentEntity);

        return modelMapper.map(updatedComment , CommentDto.class);

    }

    @Override
    public void deleteCommentById(Long Id) {

        commentRepository.deleteById(Id);
    }

    @Override
    public List<CommentDto> getCommentByPage(int pageNo, int pageSize , String orderBy , String sortDir) {

        Sort sort = sortDir.equals("asc") ? Sort.by(Sort.Order.asc(orderBy)) : Sort.by(Sort.Order.desc(orderBy));

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<CommentEntity> page = commentRepository.findAll(pageable);

        List<CommentEntity> comment = page.getContent();

        return comment.stream()
                .map(commentEntity -> modelMapper.map(commentEntity, CommentDto.class))
                .collect(Collectors.toList());

    }
}