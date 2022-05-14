package com.example.backend.service;

import com.example.backend.entity.Comment;

import java.util.List;

public interface CommentService{
    List<Comment> getAllCommentsForPost(Long postId);
    void deleteComment(Long commentId);
}
