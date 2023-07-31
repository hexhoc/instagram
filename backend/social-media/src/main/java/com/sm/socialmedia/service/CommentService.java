package com.sm.socialmedia.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import com.sm.socialmedia.dto.comment.CommentCommand;
import com.sm.socialmedia.dto.comment.CommentQuery;
import com.sm.socialmedia.entity.Comment;
import com.sm.socialmedia.mapper.CommentMapper;
import com.sm.socialmedia.messages.MessageSender;
import com.sm.socialmedia.repository.CommentRepository;
import com.sm.socialmedia.repository.specification.CommentSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final MessageSender messageSender;

    public List<CommentQuery> findAllByCriteria(Integer userId, Integer postId, Integer page, Integer limit) {
        var specification = new CommentSpecification(userId, postId);
        return commentRepository.findAll(specification, PageRequest.of(page, limit))
                                .map(commentMapper::toDto)
                                .toList();
    }

    public CommentQuery findById(String id) {
        return commentMapper.toDto(requireOne(id));
    }

    private Comment requireOne(String id) {
        return commentRepository.findById(UUID.fromString(id))
                                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

    public void create(CommentCommand commentCommand) {
        var comment = commentRepository.save(commentMapper.toEntity(commentCommand));
        messageSender.sendCommentNotification(comment);
    }

    @Transactional
    public void update(String id, CommentCommand commentCommand) {
        commentRepository.update(UUID.fromString(id), commentCommand);
    }

    @Transactional
    public void deleteById(String id) {
        commentRepository.deleteById(UUID.fromString(id));
    }

}
