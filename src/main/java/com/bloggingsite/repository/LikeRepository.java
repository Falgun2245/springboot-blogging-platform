package com.bloggingsite.repository;

import com.bloggingsite.entity.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<LikeEntity , Long> {

    boolean findByUserIdAndPostId(Long userId , Long postId);
}