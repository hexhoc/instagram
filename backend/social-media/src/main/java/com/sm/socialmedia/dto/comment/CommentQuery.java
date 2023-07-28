package com.sm.socialmedia.dto.comment;

import java.time.LocalDateTime;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Query for a comment")
public class CommentQuery {
    
    @Schema(description = "Comment id")
    private UUID id;

    @Schema(description = "Comment postId")
    private UUID postId;

    @Schema(description = "Comment message")
    private String message;

    @Schema(description = "Comment createdAt")
    private LocalDateTime createdAt;

    @Schema(description = "Comment updatedAt")
    private LocalDateTime updatedAt;
}