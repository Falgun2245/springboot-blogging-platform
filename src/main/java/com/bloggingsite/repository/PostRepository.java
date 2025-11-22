package com.bloggingsite.repository;

import com.bloggingsite.entity.CategoryEntity;
import com.bloggingsite.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<PostEntity , Long> {

    List<PostEntity> findByTitleContaining(String title);

    Optional<PostEntity> findById(Long id);

    List<PostEntity> findByCategory(CategoryEntity category);
}