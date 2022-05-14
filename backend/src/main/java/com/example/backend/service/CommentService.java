package com.example.backend.service;

import com.example.backend.dto.CommentDTO;
import com.example.backend.entity.Comment;

import java.security.Principal;
import java.util.List;

public interface CommentService{
    Comment saveComment(Long postId, CommentDTO commentDTO, Principal principal);
    List<Comment> getAllCommentsForPost(Long postId);
    void deleteComment(Long commentId);
}
