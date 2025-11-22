package com.bloggingsite.service.impl;

import com.bloggingsite.DTO.PostDto;
import com.bloggingsite.custom_exception.IdAlreadyExistException;
import com.bloggingsite.custom_exception.ResourceNotFoundByStr;
import com.bloggingsite.custom_exception.ResourceNotFoundException;
import com.bloggingsite.entity.CategoryEntity;
import com.bloggingsite.entity.PostEntity;
import com.bloggingsite.entity.UserEntity;
import com.bloggingsite.paylot.PostResponse;
import com.bloggingsite.repository.CategoryRepository;
import com.bloggingsite.repository.PostRepository;
import com.bloggingsite.repository.UserRepository;
import com.bloggingsite.service.PostService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImp implements PostService {


    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ImageServiceImp imageServiceImp;



    @Transactional
    @Override
    public PostDto createUserPost(PostDto postDto , Long myId , Long Id , MultipartFile[] multipartFile , String path) throws ResourceNotFoundException, IOException {

        UserEntity userEntity = userRepository.findById(myId).orElseThrow(() -> new ResourceNotFoundException("user" , "id" , myId));
        CategoryEntity categoryEntity = categoryRepository.findById(Id).orElseThrow(() -> new ResourceNotFoundException("user" , "id" , Id));
        PostEntity postEntity = modelMapper.map(postDto , PostEntity.class);

        if(isExistUserId(userEntity.getId())) {

            throw new IdAlreadyExistException(myId , "Already Exist!!!");
        }
        if(isExistCategoryId(categoryEntity.getId())) {

            throw new IdAlreadyExistException(Id , "Already Exist!!!");
        }

        postEntity.setImage_name(imageServiceImp.PostImage(path , multipartFile));
        postEntity.setCategory(categoryEntity);
        postEntity.setUserEntity(userEntity);
        postEntity.setPostCreateTime(LocalDateTime.now());
        postEntity.setPostUpdateTime(LocalDateTime.now());

        PostEntity createPost = postRepository.save(postEntity);

        return modelMapper.map(createPost , PostDto.class);
    }


    @Override
    public List<PostDto> getAllUserPost(Integer pageNo , Integer pageSize) {

        Pageable page = PageRequest.of(pageNo , pageSize);

        Page<PostEntity> page1 = postRepository.findAll(page);

        List<PostEntity> postEntities = page1.getContent();

        return postEntities.stream()
                .map(postEntity -> modelMapper.map(postEntity , PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDto getUserPostById(Long myId) throws ResourceNotFoundException {

        PostEntity postEntity = postRepository.findById(myId).orElseThrow(() -> new ResourceNotFoundException("user" , "id" , myId));

        return modelMapper.map(postEntity , PostDto.class);
    }

    @Override
    public PostDto updateUserPostById(Long id, PostDto postDto) throws ResourceNotFoundException {

        PostEntity postEntity = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user" , "id" , id));

        postEntity.setImage_name(postDto.getImage_name());
        postEntity.setPost_title(postDto.getPost_title());
        postEntity.setPost_content(postDto.getPost_content());
        postEntity.setPostUpdateTime(LocalDateTime.now());

        PostEntity post = postRepository.save(postEntity);

        return modelMapper.map(post , PostDto.class);
    }

    @Override
    public void deleteUserPostById(Long id) {

        postRepository.deleteById(id);
    }

    @Override
    public List<PostDto> getPostByTitle(String postTitle) {

        List<PostEntity> post = postRepository.findByTitleContaining(postTitle);

        return post.stream()
                .map(postEntity -> modelMapper.map(postEntity , PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostResponse getAllPostByPage(int pageNo, int pageSize , String sortBy , String orderBy) {

        Sort sort = orderBy.equalsIgnoreCase("dsc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable page = PageRequest.of(pageNo , pageSize , sort);

        Page<PostEntity> page1 = postRepository.findAll(page);

        List<PostEntity> postEntities = page1.getContent();

        List<PostDto> post =  postEntities.stream()
                .map(postEntity -> modelMapper.map(postEntity , PostDto.class))
                .collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();

        postResponse.setContent(post);
        postResponse.setCurrentNumberOfPost(page1.getNumberOfElements());
        postResponse.setTotalNumberOfPost(page1.getTotalElements());
        postResponse.setCurrentNumberOfPage(page1.getNumber());
        postResponse.setLastNumberOfPage(page1.isLast());
        postResponse.setTotalNumberOfPage(page1.getTotalPages());

        return postResponse;
    }

    @Override
    public List<PostDto> getCategoryByName(String categoryName) {

        CategoryEntity category = categoryRepository.findByCategoryName(categoryName).orElseThrow(() -> new ResourceNotFoundByStr("category" , "categoryName" , categoryName));

        List<PostEntity> post = postRepository.findByCategory(category);

        return  post.stream()
                .map(postEntity -> modelMapper.map(postEntity , PostDto.class))
                .collect(Collectors.toList());
    }

    public boolean isExistUserId(Long id) {

        return postRepository.findById(id).isPresent();
    }

    public boolean isExistCategoryId(Long id) {

        // exist Category is one type of Repository.

        return postRepository.findById(id).isPresent();
    }
}