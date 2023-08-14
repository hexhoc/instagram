package com.sm.socialmedia.controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import com.sm.socialmedia.entity.MediaContent;
import com.sm.socialmedia.service.MediaContentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/media")
@CrossOrigin
@RequiredArgsConstructor
@Tag(name = "Media content", description = "Media contents API")
@Log4j2
public class MediaContentController {

    private final MediaContentService mediaContentService;

    @GetMapping("/users/{userId}")
    public ResponseEntity<MediaContent> getUserMedia(@PathVariable UUID userId) {
        var mediaContent = mediaContentService.getUserMedia(userId);
        return new ResponseEntity<>(mediaContent, HttpStatus.OK);
    }

    @PostMapping("/users/{userId}")
    public ResponseEntity<String> uploadUserMedia(@PathVariable UUID userId,
                                                  @RequestParam MultipartFile file) throws IOException {

        mediaContentService.uploadUserMedia(file, userId);
        return ResponseEntity.ok("Media uploaded Successfully");
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<List<MediaContent>> getPostMedia(@PathVariable UUID postId) {
        var mediaContents = mediaContentService.getPostMedia(postId);
        return new ResponseEntity<>(mediaContents, HttpStatus.OK);
    }

    @PostMapping("/posts/{postId}")
    public ResponseEntity<String> uploadImageToPost(@PathVariable UUID postId,
                                                    @RequestParam MultipartFile file) throws IOException {
        mediaContentService.uploadPostMedia(file, postId);
        return ResponseEntity.ok("Media uploaded Successfully");
    }

    @DeleteMapping("/{mediaId}")
    public ResponseEntity<String> delete(@PathVariable String mediaId) {
        //TODO
        return ResponseEntity.ok("Media deleted Successfully");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteBatch() {
        //TODO
        return ResponseEntity.ok("Media deleted Successfully");
    }

}
