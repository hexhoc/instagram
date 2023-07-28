package com.sm.socialmedia.dto.post;

import java.time.LocalDateTime;
import java.util.UUID;

import com.sm.socialmedia.entity.Group;
import com.sm.socialmedia.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Command to create or update a post")
public class PostCommand {

    @Schema(description = "Post title")
    @NotNull
    @NotBlank(message = "Title cannot be empty")
    @Size(min = 5, message = "Title size is too short (min = 5)")
    @Size(max = 255, message = "Title size is too long (max = 255)")
    private String title;

    @Schema(description = "Post content")
    @NotNull
    @NotBlank(message = "Content cannot be empty")
    private String content;

    @Schema(description = "Post userId")
    @NotNull
    private UUID userId;

    @Schema(description = "Post groupId")
    @NotNull
    private UUID groupId;
}
