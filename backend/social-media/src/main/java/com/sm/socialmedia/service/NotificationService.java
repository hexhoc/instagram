package com.sm.socialmedia.service;

import com.sm.socialmedia.dto.comment.CommentCommand;
import com.sm.socialmedia.dto.notification.NotificationCommand;
import com.sm.socialmedia.entity.Notification;
import com.sm.socialmedia.entity.NotificationTypeEnum;
import com.sm.socialmedia.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
}
