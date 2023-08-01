package com.sm.socialmedia.controller;

import java.util.List;
import java.util.UUID;

import com.sm.socialmedia.dto.comment.CommentCommand;
import com.sm.socialmedia.dto.comment.CommentQuery;
import com.sm.socialmedia.entity.Notification;
import com.sm.socialmedia.service.CommentService;
import com.sm.socialmedia.service.NotificationService;
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
@RequestMapping("/api/v1/notification")
@Tag(name = "Notification", description = "Notification API")
@Log4j2
public class NotificationController {

    private final NotificationService notificationService;

    @Operation(summary = "Get notification list", description = "Get notification list")
    @GetMapping
    public ResponseEntity<List<Notification>> getNotifications(
            @Parameter(description="user id filter")
            @RequestParam(name = "userId", required = false) UUID userId,
            @Parameter(description="Page, default is 0")
            @RequestParam(name = "Page", required = false, defaultValue = "0") Integer page,
            @Parameter(description="limit, default is 10")
            @RequestParam(name = "limit", required = false, defaultValue = "10") Integer limit) {

        log.info("get notifications list");
        return ResponseEntity.ok(notificationService.findAllByUserId(userId, page, limit));
    }

}
