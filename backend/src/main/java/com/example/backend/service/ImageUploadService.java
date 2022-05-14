package com.example.backend.service;

import com.example.backend.entity.ImageModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

public interface ImageUploadService {
    ImageModel uploadImageToUser(MultipartFile file, Principal principal) throws IOException;
    ImageModel uploadImageToPost(MultipartFile file, Principal principal, Long postId) throws IOException;
    ImageModel getImageToUser(Principal principal);
    ImageModel getImageToPost(Long postId);

}
