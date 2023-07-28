package com.sm.socialmedia.dto.post;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "The command to set a like")
public class PostLikeCommand {
    @Schema(description = "The user who put a like")
    @NotNull
    private UUID userId;

    @Schema(description = "The post id to which the like is set")
    @NotNull
    private UUID postId;
}
