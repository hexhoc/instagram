package com.sm.socialmedia.controller;

import java.util.List;
import java.util.UUID;

import com.sm.socialmedia.dto.post.PostCommand;
import com.sm.socialmedia.dto.post.PostLikeCommand;
import com.sm.socialmedia.dto.post.PostQuery;
import com.sm.socialmedia.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/v1/posts")
@Tag(name = "Posts", description = "Posts API")
@Log4j2
public class PostController {

    private final PostService postService;

    @Operation(summary = "Get posts list", description = "Get posts list")
    @GetMapping
    public ResponseEntity<List<PostQuery>> getPosts(
            @Parameter(description="user id filter")
            @RequestParam(name = "userId", required = false) UUID userId,
            @Parameter(description="group id filter")
            @RequestParam(name = "groupId", required = false) UUID groupId,
            @Parameter(description="Page, default is 0")
            @RequestParam(name = "Page", required = false, defaultValue = "0") Integer page,
            @Parameter(description="limit, default is 10")
            @RequestParam(name = "limit", required = false, defaultValue = "10") Integer limit) {

        log.info("get posts list");
        return ResponseEntity.ok(postService.findAllByCriteria(userId, groupId, page, limit));
    }

    @Operation(summary = "Find post by ID", description = "Return single post")
    @GetMapping(path = "/{id}")
    public ResponseEntity<PostQuery> findById(@Valid @NotNull @PathVariable(name = "id") String id) {
        log.info("get post by id");
        return ResponseEntity.ok(postService.findById(id));
    }

    @Operation(summary = "Create new post", description = "Create new post")
    @PostMapping
    public ResponseEntity<String> create(
            @Parameter(description="Post to create. Cannot null or empty.", required=true)
            @RequestBody PostCommand postCommand) {
        log.info("Add new post");
        postService.create(postCommand);
        return ResponseEntity.ok("Successfully saved");
    }

    @Operation(summary = "Update an existing post", description = "Return updated post")
    @PutMapping("/{id}")
    public ResponseEntity<String> update(
            @Parameter(description="Post id. Cannot null or empty.", required=true)
            @Valid @NotNull @PathVariable("id") String id,
            @Parameter(description="Post to update. Cannot null or empty.", required=true)
            @RequestBody PostCommand postCommand) {
        log.info("update post");
        postService.update(id, postCommand);
        return ResponseEntity.ok("Successfully updated");
    }

    @Operation(summary = "Delete post by ID", description = "Return \"OK\"")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteById(
            @Parameter(description="Id of the post to be delete. Cannot be empty.", required=true)
            @PathVariable(name = "id") String id) {
        postService.deleteById(id);
        log.info("delete post");
        return ResponseEntity.ok("Successfully deleted");
    }

    @Operation(summary = "Like post", description = "Like post")
    @PostMapping("/like")
    public ResponseEntity<String> like(
            @Parameter(description="Post like command with user id and post id", required=true)
            @RequestBody PostLikeCommand postLikeCommand) {
        log.info("Like post");
        postService.like(postLikeCommand);
        return ResponseEntity.ok("Successfully saved");
    }
}
