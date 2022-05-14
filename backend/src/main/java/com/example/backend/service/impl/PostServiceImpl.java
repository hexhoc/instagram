package com.example.backend.service.impl;

import com.example.backend.dto.PostDTO;
import com.example.backend.entity.ImageModel;
import com.example.backend.entity.Post;
import com.example.backend.entity.User;
import com.example.backend.exception.PostNotFoundException;
import com.example.backend.repository.ImageRepository;
import com.example.backend.repository.PostRepository;
import com.example.backend.service.PostService;
import com.example.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    public static final Logger LOG = LoggerFactory.getLogger(PostServiceImpl.class);
    private final PostRepository postRepository;
    private final UserService userService;
    private final ImageRepository imageRepository;


    @Override
    public Post createPost(PostDTO postDTO, Principal principal) {
        User user = userService.getUserByPrincipal(principal);

        Post post = new Post();
        post.setUser(user);
        post.setCaption(postDTO.getCaption());
        post.setLocation(postDTO.getLocation());
        post.setTitle(postDTO.getTitle());
        post.setLikes(0);

        LOG.info(String.format("Saving new post for user: %s", user.getUsername()));

        return postRepository.save(post);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAllByOrderByCreatedDateDesc();
    }

    @Override
    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(
                        () -> new PostNotFoundException("Post cannot be found"));
    }
    @Override
    public Post getPostById(Long id, Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        return postRepository.findByIdAndUser(id, user)
                .orElseThrow(
                        () -> new PostNotFoundException(
                                String.format("Post cannot be found for username: %s", user.getUsername()
                                )
                        ));
    }

    @Override
    public List<Post> getAllPostForUser(Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        return postRepository.findAllByUser(user);
    }

    @Override
    public Post likePost(Long postId, String username) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new PostNotFoundException("Post cannot be found"));

        Optional<String> userLiked = post.getLikedUsers()
                .stream()
                .filter(u->u.equals(username))
                .findAny();

        if (userLiked.isPresent()) {
            post.setLikes(post.getLikes()-1);
            post.getLikedUsers().remove(userLiked.get());
        } else {
            post.setLikes(post.getLikes()+1);
            post.getLikedUsers().add(username);
        }

        return postRepository.save(post);
    }

    @Override
    public void deletePost(Long postId, Principal principal) {
        Post post = getPostById(postId, principal);
        Optional<ImageModel> imageModelOpt= imageRepository.findByPostId(postId);
        postRepository.delete(post);
        imageModelOpt.ifPresent(imageRepository::delete);
    }
}
