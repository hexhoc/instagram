package com.sm.socialmedia.repository;

import java.util.UUID;

import com.sm.socialmedia.entity.Group;
import com.sm.socialmedia.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, UUID> {
}
