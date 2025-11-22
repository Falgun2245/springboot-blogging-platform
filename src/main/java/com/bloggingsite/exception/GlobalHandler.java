package com.bloggingsite.exception;

import com.bloggingsite.custom_exception.*;
import com.bloggingsite.paylot.ApiResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException r1) {

        String message = r1.getMessage();
        ApiResponse apiResponse = new ApiResponse(message , false);
        return new ResponseEntity<ApiResponse>(apiResponse , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String , String>> handleMethodArgsNotValidException(MethodArgumentNotValidException m1) {

        Map<String , String> bindingResultMap = new HashMap<>();

        m1.getBindingResult().getAllErrors().forEach(objectError -> {

            String filed = ((FieldError) objectError).getField();
            String value = objectError.getDefaultMessage();

            bindingResultMap.put(filed , value);
        });

        return new ResponseEntity<Map<String , String>>(bindingResultMap , HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<ApiResponse> handleAlreadyExistException(AlreadyExistException a1) {

        String message = a1.getMessage();

        ApiResponse apiResponse = new ApiResponse(message , false);

        return new ResponseEntity<ApiResponse>(apiResponse , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NameAlreadyExistException.class)
    public ResponseEntity<ApiResponse> handleCategoryNameExistException(NameAlreadyExistException c1) {

        String message = c1.getMessage();

        ApiResponse apiResponse  = new ApiResponse(message , false);

        return new ResponseEntity<ApiResponse>(apiResponse , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IdAlreadyExistException.class)
    public ResponseEntity<ApiResponse> handleIdAlreadyExistException(IdAlreadyExistException a1) {

        String message = a1.getMessage();

        ApiResponse apiResponse = new ApiResponse(message , false);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @ExceptionHandler(EmptyException.class)
    public ResponseEntity<ApiResponse> handleEmptyException(EmptyException e1) {

        String emptyMessage = e1.getMessage();

        ApiResponse apiResponse = new ApiResponse(emptyMessage , false);

        return new ResponseEntity<ApiResponse>(apiResponse ,  HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundByStr.class)
    public ResponseEntity<ApiResponse> handleNameException(ResourceNotFoundByStr r1) {

        String nameMessage = r1.getMessage();

        ApiResponse apiResponse = new ApiResponse(nameMessage , false);

        return new ResponseEntity<ApiResponse>(apiResponse , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<ApiResponse> handleIdNotFoundException(IdNotFoundException e1) {

        String message = e1.getMessage();
        ApiResponse apiResponse = new ApiResponse(message , false);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @ExceptionHandler(NotExistException.class)
    public ResponseEntity<ApiResponse> handleNoExistException(NotExistException n1) {

        String message = n1.getMessage();

        ApiResponse apiResponse = new ApiResponse(message , false);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    // Practise and Understanding

//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<UserPostApiResponse> handleUserAPiResponse(ResourceNotFoundException r1) {
//
//        String message = r1.getMessage();
//        UserPostApiResponse userPostApiResponse = new UserPostApiResponse(message , false);
//
//        return new ResponseEntity<UserPostApiResponse>(userPostApiResponse , HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Map<String , String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException m1) {
//
//        Map<String , String> map = new HashMap<>();
//
//        m1.getBindingResult().getAllErrors().forEach(error -> {
//
//            String filed = ((FieldError) error).getField();
//
//            String value = error.getDefaultMessage();
//
//            map.put(filed, value);
//        });
//
//        return new ResponseEntity<Map<String , String>>(map , HttpStatus.BAD_REQUEST);
//    }
}