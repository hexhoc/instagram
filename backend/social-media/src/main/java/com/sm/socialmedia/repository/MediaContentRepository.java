package com.sm.socialmedia.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.sm.socialmedia.entity.MediaContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaContentRepository extends JpaRepository<MediaContent, UUID> {
    Optional<MediaContent> findByUserId(UUID userId);
    List<MediaContent> findAllByPostId(UUID postId);
}
