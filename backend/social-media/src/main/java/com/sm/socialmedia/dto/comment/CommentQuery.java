package com.sm.socialmedia.dto.comment;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentQuery {
    //TODO: add description and constraint
    private UUID id;
    private UUID postId;
    private String message;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}