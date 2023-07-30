package com.sm.socialmedia.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.sm.socialmedia.dto.post.PostCommand;
import com.sm.socialmedia.dto.post.PostLikeCommand;
import com.sm.socialmedia.dto.post.PostQuery;
import com.sm.socialmedia.entity.Group;
import com.sm.socialmedia.entity.Post;
import com.sm.socialmedia.entity.PostAnalytic;
import com.sm.socialmedia.entity.PostUserLike;
import com.sm.socialmedia.entity.User;
import com.sm.socialmedia.mapper.PostMapper;
import com.sm.socialmedia.repository.PostAnalyticRepository;
import com.sm.socialmedia.repository.PostRepository;
import com.sm.socialmedia.repository.PostUserLikeRepository;
import com.sm.socialmedia.repository.specification.PostSpecification;
import org.aspectj.apache.bcel.Repository;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    private PostRepository postRepository;
    @Mock
    private PostUserLikeRepository postUserLikeRepository;
    @Mock
    private PostAnalyticRepository postAnalyticRepository;
    @Spy
    private PostMapper postMapper = Mappers.getMapper(PostMapper.class );;

    @InjectMocks
    private PostService postService;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("Test find all by criteria")
    void findAllByCriteria() {
        when(postRepository.findAll(Mockito.any(PostSpecification.class), Mockito.any(PageRequest.class)))
                .thenReturn(new PageImpl<>(List.of(post())));

        var result = postService.findAllByCriteria(
                UUID.fromString("c589fd51-98d1-4b39-b5a0-fac8d309501f"),
                UUID.fromString("c589fd51-98d1-4b39-b5a0-fac8d309490f"),
                0,
                10);

        assertThat(result).hasSize(1);

        var post = result.get(0);
        assertEquals("title", post.getTitle());
        assertEquals("content", post.getContent());
        assertEquals("c589fd51-98d1-4b39-b5a0-fac8d309501f", post.getUserId());
        assertEquals("c589fd51-98d1-4b39-b5a0-fac8d309490f", post.getGroupId());

    }

    @Test
    @DisplayName("Test create")
    void create() {
        when(postRepository.save(Mockito.any())).thenReturn(post());
        assertDoesNotThrow(() -> postService.create(postCommand()));
    }

    @Test
    @DisplayName("Test update")
    void update() {
        doNothing().when(postRepository).update(Mockito.any(), Mockito.any());
        assertDoesNotThrow(() -> postService.update("a589fd51-98d1-4b39-b5a0-fac8d309501f", postCommand()));
    }

    @Test
    @DisplayName("Test delete by id")
    void deleteById() {
        doNothing().when(postRepository).deleteById(Mockito.any());
        assertDoesNotThrow(() -> postService.deleteById("a589fd51-98d1-4b39-b5a0-fac8d309501f"));
    }

    @Test
    @DisplayName("Test like")
    void like() {
        when(postAnalyticRepository.findByPostId(Mockito.any()))
                .thenReturn(Optional.of(postAnalytic()));
        when(postUserLikeRepository.findByUserIdAndPostId(Mockito.any(),Mockito.any()))
                .thenReturn(Optional.of(postUserLike()));
        doNothing().when(postUserLikeRepository).deleteById(Mockito.any());
        when(postAnalyticRepository.save(Mockito.any())).thenReturn(postAnalytic());
//        when(postUserLikeRepository.save(Mockito.any())).thenReturn(postUserLike());

        postService.like(postLikeCommand());
    }

    Post post() {
        var u = new User();
        u.setId(UUID.fromString("c589fd51-98d1-4b39-b5a0-fac8d309501f"));
        u.setBio("bio");
        u.setEmail("test@test.com");
        u.setFirstName("firstName");
        u.setLastName("lastName");
        u.setUsername("username");

        var g = new Group();
        g.setId(UUID.fromString("c589fd51-98d1-4b39-b5a0-fac8d309490f"));
        g.setName("groupname");
        g.setDescription("description");
        g.setCreator(u);;

        var p = new Post();
        p.setId(UUID.fromString("a589fd51-98d1-4b39-b5a0-fac8d309501f"));
        p.setTitle("title");
        p.setContent("content");
        p.setUser(u);
        p.setUserId(u.getId());
        p.setGroup(g);
        p.setGroupId(g.getId());

        return p;
    }

    PostCommand postCommand() {
        var p = new PostCommand();
        p.setTitle("title");
        p.setContent("content");
        p.setGroupId(UUID.fromString("c589fd51-98d1-4b39-b5a0-fac8d309490f"));
        p.setUserId(UUID.fromString("c589fd51-98d1-4b39-b5a0-fac8d309501f"));

        return p;
    }

    PostLikeCommand postLikeCommand() {
        var p = new PostLikeCommand();
        p.setPostId(UUID.fromString("a589fd51-98d1-4b39-b5a0-fac8d309501f"));
        p.setUserId(UUID.fromString("c589fd51-98d1-4b39-b5a0-fac8d309501f"));

        return p;
    }

    PostAnalytic postAnalytic() {
        var p = new PostAnalytic();
        p.setId(UUID.fromString("a589fd51-98d1-4b39-b5a0-fac8d309400b"));
        p.setPostId(UUID.fromString("a589fd51-98d1-4b39-b5a0-fac8d309501f"));
        p.setWatched(10);
        p.setLikes(10);

        return p;
    }

    PostUserLike postUserLike() {
        var p = new PostUserLike();
        p.setId(UUID.fromString("a589fd51-98d1-4b39-b5a0-fac8d309501f"));
        p.setUserId(UUID.fromString("c589fd51-98d1-4b39-b5a0-fac8d309501f"));
        p.setPostId(UUID.fromString("a589fd51-98d1-4b39-b5a0-fac8d309501f"));
        p.setLikedAt(LocalDateTime.now());

        return p;
    }
}