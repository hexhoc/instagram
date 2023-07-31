package com.sm.socialmedia;

import java.util.Objects;

import com.sm.socialmedia.entity.Post;
import com.sm.socialmedia.entity.PostAnalytic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SocialMediaApplication {
    //TODO: add elasticsearch
    //TODO: add notification service
    //TODO: add feed service
    //TODO: direct message
    //TODO: add security constraint
    //TODO: add cache (memoizer)
    //TODO: make postService.like asynced

    public static void main(String[] args) {
        SpringApplication.run(SocialMediaApplication.class, args);
    }

}
