package com.sm.socialmedia.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.sm.socialmedia.entity.UserFollow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFollowRepository extends JpaRepository<UserFollow, UUID> {
    List<UserFollow> findAllByFollowingId(UUID userId);
    List<UserFollow> findAllByUserId(UUID userId);

    Optional<UserFollow> findByUserIdAndFollowingId(UUID userId, UUID followingId);

}
