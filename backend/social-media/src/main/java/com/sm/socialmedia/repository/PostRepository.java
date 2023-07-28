package com.sm.socialmedia.repository;

import java.util.UUID;

import com.sm.socialmedia.dto.post.PostCommand;
import com.sm.socialmedia.entity.Group;
import com.sm.socialmedia.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID>, JpaSpecificationExecutor<Post> {

    @Modifying
    @Query(value = """
            UPDATE Post p SET
            p.title = :#{#post.title},
            p.content = :#{#post.content},
            p.userId = :#{#post.userId},
            p.groupId = :#{#post.groupId}
            WHERE p.id = :id
            """)
    void update(@Param("id") UUID id,
                @Param("post") PostCommand postCommand);

}
