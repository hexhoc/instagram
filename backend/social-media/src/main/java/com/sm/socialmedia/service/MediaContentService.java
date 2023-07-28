package com.sm.socialmedia.service;

import com.sm.socialmedia.entity.MediaContent;
import com.sm.socialmedia.repository.MediaContentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
@RequiredArgsConstructor
@Log4j2
public class MediaContentService {

    private final MediaContentRepository mediaContentRepository;

    public MediaContent getUserMedia(UUID userId) {
        AtomicReference<MediaContent> mediaContentRef = new AtomicReference<>(new MediaContent());

        mediaContentRepository.findByUserId(userId).ifPresent(m -> {
            m.setImageBytes(decompressBytes(m.getImageBytes()));
            mediaContentRef.set(m);
        });

        return mediaContentRef.get();
    }

    public List<MediaContent> getPostMedia(UUID postId) {
        var mediaContents = mediaContentRepository.findAllByPostId(postId);
        mediaContents.forEach(mediaContent -> {
            mediaContent.setImageBytes(decompressBytes(mediaContent.getImageBytes()));
        });

        return mediaContents;
    }

    public void uploadUserMedia(MultipartFile file, UUID userId) throws IOException {
        log.info("Uploading image profile to user {}", userId);

        mediaContentRepository.findByUserId(userId).ifPresent(mediaContentRepository::delete);

        var mediaContent = new MediaContent();
        mediaContent.setUserId(userId);
        mediaContent.setImageBytes(compressBytes(file.getBytes()));
        mediaContent.setName(file.getOriginalFilename());

        mediaContentRepository.save(mediaContent);
    }

    public void uploadPostMedia(MultipartFile file, UUID postId) throws IOException {

        log.info("Uploading image profile to post {}", postId);

        var mediaContent = new MediaContent();
        mediaContent.setPostId(postId);
        mediaContent.setImageBytes(compressBytes(file.getBytes()));
        mediaContent.setName(file.getOriginalFilename());

        mediaContentRepository.save(mediaContent);
    }


    private byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            log.error("Cannot compress Bytes");
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }

    private static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException | DataFormatException e) {
            log.error("Cannot decompress Bytes");
        }
        return outputStream.toByteArray();
    }

}
