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
public class PostCommand {
    private UUID id;
    private String title;
    private String content;
    private UUID userId;
    private UUID groupId;
}
