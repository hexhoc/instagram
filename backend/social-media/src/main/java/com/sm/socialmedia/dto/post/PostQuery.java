package com.sm.socialmedia.dto.post;

import java.time.LocalDateTime;
import java.util.UUID;

import com.sm.socialmedia.entity.Group;
import com.sm.socialmedia.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Post information")
public class PostQuery {
    @Schema(description = "Post id")
    private UUID id;
    private String title;
    private String content;
    @Schema(description = "Username of user who created post ")
    private String username;
    @Schema(description = "Id of user who created post ")
    private String userId;
    @Schema(description = "Group name where post is publish")
    private String groupName;
    @Schema(description = "Group id where post is publish")
    private String groupId;
    @Schema(description = "Group creator username")
    private String groupCreatorUsername;
    @Schema(description = "Group creator id")
    private String groupCreatorId;
    @Schema(description = "Number of likes")
    private Integer likes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer version;
}
