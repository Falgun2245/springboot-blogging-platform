package com.bloggingsite.service.impl;

import com.bloggingsite.DTO.CategoryDto;
import com.bloggingsite.custom_exception.AlreadyExistException;
import com.bloggingsite.custom_exception.ResourceNotFoundException;
import com.bloggingsite.entity.CategoryEntity;
import com.bloggingsite.repository.CategoryRepository;
import com.bloggingsite.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImp implements CategoryService {


    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {

        CategoryEntity category = mapper.map(categoryDto , CategoryEntity.class);

        category.setCategoryCreateTime(LocalDateTime.now());
        category.setCategoryUpdateTime(LocalDateTime.now());

        if(IsExistCategoryName(category.getCategoryName())) {

            throw new AlreadyExistException("Category Name is Already Exist!!!");
        }

        CategoryEntity createdCategory = categoryRepository.save(category);

        return mapper.map(createdCategory , CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategory() {

        List<CategoryEntity> getAllCategoryEntity = categoryRepository.findAll();

        return  getAllCategoryEntity.stream()
                .map(category1 -> mapper.map(category1 , CategoryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryById(Long id) throws ResourceNotFoundException {

        CategoryEntity findCategoryById = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user" , "id" , id));

        return mapper.map(findCategoryById , CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategoryById(Long id, CategoryDto categoryDto) throws ResourceNotFoundException {

        CategoryEntity findCategoryById = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user" , "id" , id));

        findCategoryById.setCategoryName(categoryDto.getCategoryName());
        findCategoryById.setCategoryDescription(categoryDto.getCategoryDescription());
        findCategoryById.setCategoryImage(categoryDto.getCategoryImage());
        findCategoryById.setCategoryCreateTime(LocalDateTime.now());
        findCategoryById.setCategoryUpdateTime(LocalDateTime.now());

        CategoryEntity updatedCategory = categoryRepository.save(findCategoryById);

        return mapper.map(updatedCategory , CategoryDto.class);
    }

    @Override
    public void deleteCategoryById(Long id) {

        categoryRepository.deleteById(id);
    }

    @Override
    public List<CategoryDto> SearchCategoryName(String categoryName) {

        List<CategoryEntity> category = categoryRepository.findByCategoryNameContaining(categoryName);

        if(category.isEmpty()) {

            throw new RuntimeException("Category Name is Empty!!!");
        }

        return category.stream()
                .map(categoryEntity -> mapper.map(categoryEntity , CategoryDto.class))
                .collect(Collectors.toList());

    }

    public boolean IsExistCategoryName(String categoryName) {

        return categoryRepository.findByCategoryName(categoryName).isPresent();
    }
}