package com.sm.socialmedia.dto.post;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostLikeCommand {
    private UUID userId;
    private UUID postId;
}
