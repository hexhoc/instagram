package com.sm.socialmedia.dto.post;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostLikeCommand {
    //TODO: add description and constraint

    private UUID userId;
    private UUID postId;
}
