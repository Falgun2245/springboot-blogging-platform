package com.bloggingsite.service;


import com.bloggingsite.DTO.CategoryDto;
import com.bloggingsite.DTO.PostDto;
import com.bloggingsite.custom_exception.ResourceNotFoundException;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);

    List<CategoryDto> getAllCategory();

    CategoryDto getCategoryById(Long id) throws ResourceNotFoundException;

    CategoryDto updateCategoryById(Long id , CategoryDto categoryDto) throws ResourceNotFoundException;

    void deleteCategoryById(Long id);

    List<CategoryDto> SearchCategoryName(String categoryName);
}