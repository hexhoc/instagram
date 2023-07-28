package com.sm.socialmedia.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import com.sm.socialmedia.dto.post.PostCommand;
import com.sm.socialmedia.dto.post.PostLikeCommand;
import com.sm.socialmedia.dto.post.PostQuery;
import com.sm.socialmedia.entity.Post;
import com.sm.socialmedia.entity.PostAnalytic;
import com.sm.socialmedia.entity.PostUserLike;
import com.sm.socialmedia.mapper.PostMapper;
import com.sm.socialmedia.repository.PostAnalyticRepository;
import com.sm.socialmedia.repository.PostUserLikeRepository;
import com.sm.socialmedia.repository.PostRepository;
import com.sm.socialmedia.repository.specification.PostSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostUserLikeRepository postUserLikeRepository;
    private final PostAnalyticRepository postAnalyticRepository;
    private final PostMapper postMapper;

    public List<PostQuery> findAllByCriteria(UUID userId, UUID groupId, Integer page, Integer limit) {
        var specification = new PostSpecification(userId, groupId);
        return postRepository.findAll(specification, PageRequest.of(page,limit))
                              .map(postMapper::toDto)
                              .toList();
    }

    public PostQuery findById(String id) {
        return postMapper.toDto(requireOne(id));
    }

    private Post requireOne(String id) {
        return postRepository.findById(UUID.fromString(id))
                              .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

    public void create(PostCommand postCommand) {
        postRepository.save(postMapper.toEntity(postCommand));
    }

    @Transactional
    public void update(String id, PostCommand postCommand) {
        postRepository.update(UUID.fromString(id), postCommand);
    }

    @Transactional
    public void deleteById(String id) {
        postRepository.deleteById(UUID.fromString(id));
    }

    @Transactional
    public void like(PostLikeCommand postLikeCommand) {
        var postAnalytic = postAnalyticRepository
                .findByPostId(postLikeCommand.getPostId())
                .orElse(PostAnalytic.builder()
                                    .postId(postLikeCommand.getPostId())
                                    .likes(0)
                                    .watched(0)
                                    .build());

        var postUserLike = postUserLikeRepository.findByUserIdAndPostId(
                postLikeCommand.getUserId(),
                postLikeCommand.getPostId());
        if (postUserLike.isPresent()) {
            postUserLikeRepository.deleteById(postUserLike.get().getId());
            postAnalytic.setLikes(postAnalytic.getLikes() - 1);
        } else {
            postUserLikeRepository.save(PostUserLike.builder()
                                                    .postId(postLikeCommand.getPostId())
                                                    .userId(postLikeCommand.getUserId())
                                                    .likedAt(LocalDateTime.now())
                                                    .build());
            postAnalytic.setLikes(postAnalytic.getLikes() + 1);
        }

        postAnalyticRepository.save(postAnalytic);
    }
}
