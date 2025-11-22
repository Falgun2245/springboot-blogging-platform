package com.bloggingsite.controller;

import com.bloggingsite.DTO.CategoryDto;
import com.bloggingsite.custom_exception.ResourceNotFoundException;
import com.bloggingsite.service.impl.CategoryServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v3/category")
public class CategoryController {

    @Autowired
    private CategoryServiceImp categoryServiceImp;

    @PostMapping("/create")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody @Valid CategoryDto categoryDto) {

        return  ResponseEntity.status(HttpStatus.CREATED).body(categoryServiceImp.createCategory(categoryDto));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CategoryDto>> getAllCategory() {

        return new ResponseEntity<List<CategoryDto>>(categoryServiceImp.getAllCategory() , HttpStatus.OK);
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("myId") Long Id) throws ResourceNotFoundException {

        return ResponseEntity.status(HttpStatus.OK).body(categoryServiceImp.getCategoryById(Id));
    }

    @PutMapping("id/{myId}")
    public ResponseEntity<CategoryDto> updateCategoryById(@PathVariable Long myId , @RequestBody @Valid CategoryDto categoryDto) throws ResourceNotFoundException {

        return ResponseEntity.status(HttpStatus.OK).body(categoryServiceImp.updateCategoryById(myId , categoryDto));
    }

    @DeleteMapping("/id/{Id}")
    public ResponseEntity<String> deleteCategoryById(@PathVariable Long Id) {

        categoryServiceImp.deleteCategoryById(Id);
        return ResponseEntity.ok("Category hase Deleted Successfully with ID : " + Id);
    }

    @GetMapping("/search")
    public ResponseEntity<List<CategoryDto>> SearchCategoryName(@RequestParam String categoryName) {

        return new ResponseEntity<List<CategoryDto>>(categoryServiceImp.SearchCategoryName(categoryName) , HttpStatus.OK);
    }
}