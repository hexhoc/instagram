package com.sm.socialmedia.dto.follow;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Command to follow or unfollow a user")
public class FollowCommand {

    @Schema(description = "Current user id")
    @NotNull
    private UUID userId;

    @Schema(description = "followed id")
    @NotNull
    private UUID followedId;
}