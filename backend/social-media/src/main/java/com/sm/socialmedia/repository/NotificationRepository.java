package com.sm.socialmedia.repository;

import java.util.UUID;

import com.sm.socialmedia.entity.GroupFollow;
import com.sm.socialmedia.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, UUID> {
}
