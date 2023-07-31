package com.sm.socialmedia.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.sm.socialmedia.entity.GroupFollow;
import com.sm.socialmedia.entity.UserFollow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupFollowRepository extends JpaRepository<GroupFollow, UUID> {

    List<GroupFollow> findAllByFollowerId(UUID userId);
    List<GroupFollow> findAllByGroupId(UUID userId);

    Optional<GroupFollow> findByFollowerIdAndGroupId(UUID userId, UUID followingId);

}
