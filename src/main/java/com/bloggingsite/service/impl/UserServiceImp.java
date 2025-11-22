package com.bloggingsite.service.impl;

import com.bloggingsite.DTO.UserDto;
import com.bloggingsite.custom_exception.AlreadyExistException;
import com.bloggingsite.entity.PostEntity;
import com.bloggingsite.entity.UserEntity;
import com.bloggingsite.custom_exception.ResourceNotFoundException;
import com.bloggingsite.repository.UserRepository;
import com.bloggingsite.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public  class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ImageServiceImp imageServiceImp;

    @Autowired
    private ModelMapper modelMapper;

    public UserDto createUser(UserDto userDto , MultipartFile multipartFile , String path) throws IOException {

        UserEntity userEntity = modelMapper.map(userDto , UserEntity.class);

        userEntity.setCreateDateTime(LocalDateTime.now());
        userEntity.setUpdateDateTime(LocalDateTime.now());

        userEntity.setUserImage(imageServiceImp.imageName(path , multipartFile));

        if(existEmail(userEntity.getEmail())) {

            throw new AlreadyExistException("Email is Already Exist!!!");
        }

        UserEntity cratedUser = userRepository.save(userEntity);

        return modelMapper.map(cratedUser , UserDto.class);
    }

    @Override
    public List<UserDto> getAllUser() {

        List<UserEntity> userEntity = userRepository.findAll();

        return userEntity.stream()
                .map(userEntity1 -> modelMapper.map(userEntity1 , UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long id) throws ResourceNotFoundException {

        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id" , "user", id));

        return modelMapper.map(userEntity , UserDto.class);
    }

    @Override
    public UserDto updateUserById(Long id, UserDto userDto) throws ResourceNotFoundException{

        UserEntity updateduserEntity = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id","user",id));

        updateduserEntity.setName(userDto.getName());
        updateduserEntity.setEmail(userDto.getEmail());
        updateduserEntity.setPassword(userDto.getPassword());
        updateduserEntity.setAbout(userDto.getAbout());
        updateduserEntity.setUpdateDateTime(LocalDateTime.now());

        UserEntity user = userRepository.save(updateduserEntity);

        return modelMapper.map(user , UserDto.class);
    }

    @Override
    public void deleteUserById(Long id) throws ResourceNotFoundException {

        if((!userRepository.existsById(id))) {

            throw new ResourceNotFoundException("id","user",id);
        }
        userRepository.deleteById(id);
    }

    public boolean existEmail(String email) {

//        String userEmail = String.valueOf(userRepository.findByEmail(email).orElse(null));

        return userRepository.findByEmail(email).isPresent();
    }
}