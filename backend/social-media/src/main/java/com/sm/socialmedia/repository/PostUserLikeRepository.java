package com.sm.socialmedia.repository;

import java.util.Optional;
import java.util.UUID;

import com.sm.socialmedia.dto.post.PostCommand;
import com.sm.socialmedia.entity.Post;
import com.sm.socialmedia.entity.PostUserLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostUserLikeRepository extends JpaRepository<PostUserLike, UUID>, JpaSpecificationExecutor<Post> {

    Optional<PostUserLike> findByUserIdAndPostId(UUID userId,
                                                 UUID postId);

}
