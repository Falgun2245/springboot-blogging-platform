package com.bloggingsite.service;


import com.bloggingsite.DTO.UserDto;
import com.bloggingsite.custom_exception.ResourceNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {

         List<UserDto> getAllUser();

         UserDto getUserById(Long id) throws ResourceNotFoundException;

         UserDto updateUserById(Long id , UserDto userDto) throws ResourceNotFoundException;

         void deleteUserById(Long id) throws ResourceNotFoundException;

         UserDto createUser(UserDto userDto , MultipartFile multipartFile , String path) throws IOException;
}