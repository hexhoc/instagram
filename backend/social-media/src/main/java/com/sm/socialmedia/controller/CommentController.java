package com.sm.socialmedia.controller;

import java.util.List;

import com.sm.socialmedia.dto.comment.CommentCommand;
import com.sm.socialmedia.dto.comment.CommentQuery;
import com.sm.socialmedia.service.CommentService;
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
@RequestMapping("/api/v1/comments")
@Tag(name = "Comments", description = "Comments API")
@Log4j2
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "Get comments list", description = "Get comments list")
    @GetMapping
    public ResponseEntity<List<CommentQuery>> getComments(
            @Parameter(description="user id filter")
            @RequestParam(name = "userId", required = false) Integer userId,
            @Parameter(description="comment id filter")
            @RequestParam(name = "postId", required = false) Integer postId,
            @Parameter(description="Page, default is 0")
            @RequestParam(name = "Page", required = false, defaultValue = "0") Integer page,
            @Parameter(description="limit, default is 10")
            @RequestParam(name = "limit", required = false, defaultValue = "10") Integer limit) {

        log.info("get comments list");
        return ResponseEntity.ok(commentService.findAllByCriteria(userId, postId, page, limit));
    }

    @Operation(summary = "Find comment by ID", description = "Return single comment")
    @GetMapping(path = "/{id}")
    public ResponseEntity<CommentQuery> findById(@Valid @NotNull @PathVariable(name = "id") String id) {
        log.info("get comment by id");
        return ResponseEntity.ok(commentService.findById(id));
    }

    @Operation(summary = "Create new comment", description = "Create new comment")
    @PostMapping
    public ResponseEntity<String> create(
            @Parameter(description="Comment to create. Cannot null or empty.", required=true)
            @RequestBody CommentCommand commentCommand) {
        log.info("Add new comment");
        commentService.create(commentCommand);
        return ResponseEntity.ok("Successfully saved");
    }

    @Operation(summary = "Update an existing comment", description = "Return updated comment")
    @PutMapping("/{id}")
    public ResponseEntity<String> update(
            @Parameter(description="Comment id. Cannot null or empty.", required=true)
            @Valid @NotNull @PathVariable("id") String id,
            @Parameter(description="Comment to update. Cannot null or empty.", required=true)
            @RequestBody CommentCommand commentCommand) {
        log.info("update comment");
        commentService.update(id, commentCommand);
        return ResponseEntity.ok("Successfully updated");
    }

    @Operation(summary = "Delete comment by ID", description = "Return \"OK\"")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteById(
            @Parameter(description="Id of the comment to be delete. Cannot be empty.", required=true)
            @PathVariable(name = "id") String id) {
        commentService.deleteById(id);
        log.info("delete comment");
        return ResponseEntity.ok("Successfully deleted");
    }

}
