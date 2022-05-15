package com.example.backend.repository;

import com.example.backend.entity.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<ImageModel, Long> {
    @Query("""
                select i from ImageModel i
                where i.postId is null and i.userId = :userId
            """)
    Optional<ImageModel> findProfileImageByUserId(Long userId);

    Optional<ImageModel> findByUserId(Long userId);

    Optional<ImageModel> findByPostId(Long postId);
}
