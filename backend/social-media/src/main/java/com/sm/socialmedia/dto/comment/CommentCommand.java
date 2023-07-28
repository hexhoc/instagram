package com.sm.socialmedia.dto.comment;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Command to create or update a comment")
public class CommentCommand {

    @Schema(description = "Comment post id")
    @NotNull
    private UUID postId;

    @Schema(description = "Comment message")
    @NotNull
    @Size(min = 1, message = "Message size is too short (min = 1)")
    @Size(max = 2000, message = "Message size is too long (max = 2000)")
    private String message;
}