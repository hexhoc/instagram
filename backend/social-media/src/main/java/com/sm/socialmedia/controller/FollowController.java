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
@RequestMapping("/api/v1")
@Tag(name = "Follows", description = "Follow API")
@Log4j2
public class FollowController {

    private final FollowService followService;

    /**
     * Get those who follow me
     * @param userId current user id
     * @return my followers
     */
    @GetMapping("/followers/{userId}")
    @Operation(summary = "Get those who follow me", responses = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    public ResponseEntity<List<UUID>> getFollowers(@PathVariable UUID userId) {
        return ResponseEntity.ok(followService.getFollowers(userId));
    }

    /**
     * Get those I follow
     * @param userId current user id
     * @return followed users
     */
    @GetMapping("/followed/{userId}")
    @Operation(summary = "Get those I follow", responses = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    public ResponseEntity<List<UUID>> getFollowed(@PathVariable UUID userId) {
        return ResponseEntity.ok(followService.getFollowed(userId));
    }

    /**
     * Get users who follow group
     * @param groupId current group id
     * @return group followers
     */
    @GetMapping("/followers/groups/{groupId}")
    @Operation(summary = "Get users following a group", responses = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Group not found"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    public ResponseEntity<List<UUID>> getFollowersGroup(@PathVariable UUID groupId) {
        return ResponseEntity.ok(followService.getGroupFollowers(groupId));
    }

    /**
     * Get the groups followed by the user
     * @param userId current user id
     * @return followed groups
     */
    @GetMapping("/followed/groups/{userId}")
    @Operation(summary = "Get current user groups", responses = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Group not found"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    public ResponseEntity<List<UUID>> getFollowedGroup(@PathVariable UUID userId) {
        return ResponseEntity.ok(followService.getFollowedGroups(userId));
    }

    /**
     * Follow or unfollow a user
     * @param followCommand
     * @return
     */
    @PostMapping("/follow")
    @Operation(summary = "Follow or unfollow a user", responses = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    public ResponseEntity<String> followOrUnfollowUser(@RequestBody FollowCommand followCommand) {
        followService.followUser(followCommand);
        return ResponseEntity.ok("OK");
    }

    /**
     * Follow or unfollow a group
     * @param followCommand
     * @return
     */
    @PostMapping("/follow/groups")
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