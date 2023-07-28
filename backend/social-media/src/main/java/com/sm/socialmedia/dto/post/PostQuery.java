package com.sm.socialmedia.dto.post;

import java.time.LocalDateTime;
import java.util.UUID;

import com.sm.socialmedia.entity.Group;
import com.sm.socialmedia.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostQuery {
    //TODO: add description and constraint

    private UUID id;
    private String title;
    private String content;
    private String username;
    private String userId;
    private String groupName;
    private String groupId;
    private String groupCreatorUsername;
    private String groupCreatorId;
    private Integer likes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer version;
}
