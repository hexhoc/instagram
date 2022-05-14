package com.example.backend.service;

import com.example.backend.dto.PostDTO;
import com.example.backend.entity.Post;

import java.security.Principal;
import java.util.List;

public interface PostService {

    Post createPost(PostDTO postDTO, Principal principal);
    List<Post> getAllPosts();
    Post getPostById(Long id);
    Post getPostById(Long id, Principal principal);
    List<Post> getAllPostForUser(Principal principal);
    Post likePost(Long postId, String username);
    void deletePost(Long postId, Principal principal);
}
