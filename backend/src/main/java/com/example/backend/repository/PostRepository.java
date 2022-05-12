package com.example.backend.repository;

import com.example.backend.entity.Post;
import com.example.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("""
                select p from Post p
                where p.user = :user
                order by p.createdDate desc
            """)
    List<Post> findAllByUser(User user);

    List<Post> findAllByOrderByCreatedDateDesc();

    Optional<Post> findByIdAndUser(Long id, User user);
}
