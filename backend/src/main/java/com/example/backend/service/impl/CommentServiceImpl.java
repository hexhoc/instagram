package com.example.backend.service.impl;

import com.example.backend.dto.CommentDTO;
import com.example.backend.entity.Comment;
import com.example.backend.entity.Post;
import com.example.backend.entity.User;
import com.example.backend.repository.CommentRepository;
import com.example.backend.service.CommentService;
import com.example.backend.service.PostService;
import com.example.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    public static final Logger LOG = LoggerFactory.getLogger(CommentServiceImpl.class);

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostService postService;


    @Override
    public Comment saveComment(Long postId, CommentDTO commentDTO, Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        Post post = postService.getPostById(postId, principal);

        Comment comment = new Comment();
        comment.setPost(post);
        comment.setUserId(user.getId());
        comment.setUsername(user.getUsername());
        comment.setMessage(commentDTO.getMessage());

        LOG.info("Saving comment for post {}", postId);

        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getAllCommentsForPost(Long postId) {
        Post post = postService.getPostById(postId);
        return commentRepository.findAllByPost(post);
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
