package com.bloggingsite.controller;

import com.bloggingsite.DTO.UserDto;
import com.bloggingsite.custom_exception.ResourceNotFoundException;
import com.bloggingsite.service.impl.UserServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserServiceImp userServiceImp;

    @Value("${image.path}")
    private String path;

    @PostMapping("/add")
    public ResponseEntity<UserDto> createUser(@RequestParam(value = "image") MultipartFile multipartFile,
                                              @RequestParam(value = "name") String name ,
                                              @RequestParam(value = "email") String email ,
                                              @RequestParam(value = "password") String password ,
                                              @RequestParam(value = "about") String about) throws IOException {

        UserDto userDto = new UserDto();
        userDto.setName(name);
        userDto.setEmail(email);
        userDto.setAbout(about);
        userDto.setPassword(password);

        return ResponseEntity.status(HttpStatus.CREATED).body(userServiceImp.createUser(userDto , multipartFile , path));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<UserDto>> getAllUsers() {

        return new ResponseEntity<>(userServiceImp.getAllUser() , HttpStatus.OK);
    }

    @GetMapping("/id{Id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("Id") Long myId) throws ResourceNotFoundException {

        return ResponseEntity.status(HttpStatus.OK).body(userServiceImp.getUserById(myId));
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<UserDto> updateUserById(@PathVariable("id") Long myid , @Valid @RequestBody  UserDto userDto) throws ResourceNotFoundException{

        return ResponseEntity.status(HttpStatus.OK).body(userServiceImp.updateUserById(myid , userDto));
    }

    @DeleteMapping("/id{myId}")
    public void deleteUserById(@PathVariable("myId") Long id) throws ResourceNotFoundException {

        userServiceImp.deleteUserById(id);
    }
}