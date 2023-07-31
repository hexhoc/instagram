package com.sm.socialmedia.controller;

import java.util.List;
import java.util.UUID;

import com.sm.socialmedia.dto.follow.FollowCommand;
import com.sm.socialmedia.service.FollowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/v1/follow")
@Tag(name = "Follows", description = "Follow API")
@Log4j2
public class FollowController {

    private final FollowService followService;

    // GET /users/{userId}/followers - Get followers for a user
    @GetMapping("/users/{userId}/followers")
    @Operation(summary = "Get followers for a user", responses = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    public ResponseEntity<List<UUID>> getFollowersForUser(@PathVariable UUID userId) {
        return ResponseEntity.ok(followService.getFollowersForUser(userId));
    }

    // GET /users/{userId}/following - Get users followed by a user
    @GetMapping("/users/{userId}/following")
    @Operation(summary = "Get users followed by a user", responses = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    public ResponseEntity<List<UUID>> getUsersFollowedByUser(@PathVariable UUID userId) {
        return ResponseEntity.ok(followService.getUsersFollowedByUser(userId));
    }

    // GET /groups/{groupId}/following - Get followers for a group
    @GetMapping("/groups/{groupId}/followers")
    @Operation(summary = "Get users following a group", responses = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Group not found"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    public ResponseEntity<List<UUID>> getFollowersForGroup(@PathVariable UUID groupId) {
        return ResponseEntity.ok(followService.getFollowersForGroup(groupId));
    }

    // GET /groups/{groupId}/following - Get users following a group
    @GetMapping("/groups/{userId}/following")
    @Operation(summary = "Get users following a group", responses = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Group not found"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    public ResponseEntity<List<UUID>> getUsersFollowingGroup(@PathVariable UUID userId) {
        return ResponseEntity.ok(followService.getUsersFollowingGroup(userId));
    }

    // POST /users/{followingId} - Follow or unfollow a user
    @PostMapping("/users")
    @Operation(summary = "Follow or unfollow a user", responses = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    public ResponseEntity<String> followOrUnfollowUser(@RequestBody FollowCommand followCommand) {
        followService.followUser(followCommand);
        return ResponseEntity.ok("OK");
    }

    // POST /groups/{groupId} - Follow a group
    @PostMapping("/groups")
    @Operation(summary = "Follow a group", responses = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Group not found"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    public ResponseEntity<String> followGroup(@RequestBody FollowCommand followCommand) {
        followService.followGroup(followCommand);
        return ResponseEntity.ok("OK");
    }

}