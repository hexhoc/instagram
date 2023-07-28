package com.sm.socialmedia.repository;

import java.util.UUID;

import com.sm.socialmedia.dto.comment.CommentCommand;
import com.sm.socialmedia.dto.post.PostCommand;
import com.sm.socialmedia.entity.Comment;
import com.sm.socialmedia.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID>, JpaSpecificationExecutor<Comment> {
    @Modifying
    @Query(value = """
            UPDATE Comment c SET
            c.postId = :#{#comment.postId},
            c.message = :#{#comment.message}
            WHERE c.id = :id
            """)
    void update(@Param("id") UUID id,
                @Param("comment") CommentCommand commentCommand);

}
