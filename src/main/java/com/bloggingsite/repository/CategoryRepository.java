package com.bloggingsite.repository;

import com.bloggingsite.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity , Long> {

    Optional<CategoryEntity> findByCategoryName(String categoryName);

    List<CategoryEntity> findByCategoryNameContaining(String categoryName);
}