package com.sm.socialmedia.service;

import java.util.List;
import java.util.UUID;

import com.sm.socialmedia.dto.comment.CommentCommand;
import com.sm.socialmedia.dto.notification.NotificationCommand;
import com.sm.socialmedia.entity.Notification;
import com.sm.socialmedia.entity.NotificationTypeEnum;
import com.sm.socialmedia.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void create(NotificationCommand notificationCommand) {
        var notification = Notification.builder()
                .userId(notificationCommand.getUserId())
                .type(NotificationTypeEnum.valueOf(notificationCommand.getType()))
                .message(notificationCommand.getMessage())
                .build();

        notificationRepository.save(notification);
    }

    public List<Notification> findAllByUserId(UUID userId, Integer page, Integer limit) {
        return notificationRepository.findAllByUserId(userId, PageRequest.of(page, limit));
    }

}
