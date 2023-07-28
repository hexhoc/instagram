package com.sm.socialmedia.dto.comment;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentCommand {
    private UUID postId;
    private String message;
}