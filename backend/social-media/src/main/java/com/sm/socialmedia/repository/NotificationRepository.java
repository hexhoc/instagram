package com.sm.socialmedia.repository;

import java.util.List;
import java.util.UUID;

import com.sm.socialmedia.entity.GroupFollow;
import com.sm.socialmedia.entity.Notification;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, UUID> {

    List<Notification> findAllByUserId(UUID userId, Pageable pageable);
}
