package com.sm.socialmedia.messages;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sm.socialmedia.config.KafkaConfig;
import com.sm.socialmedia.dto.notification.NotificationCommand;
import com.sm.socialmedia.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Log4j2
public class MessageListener {
  private final ObjectMapper objectMapper;
  private final NotificationService notificationService;

  @KafkaListener(id = "notification-service", topics = {KafkaConfig.NOTIFICATION_TOPIC})
  public void notificationEventListener(String messagePayloadJson, @Header("type") String messageType) throws Exception {
    log.info(messageType);
    if ("LikeNotificationCommand".equals(messageType)) {
      notificationReceived(objectMapper.readValue(messagePayloadJson, new TypeReference<>() {}));
    } else if ("CommentNotificationCommand".equals(messageType)) {
      notificationReceived(objectMapper.readValue(messagePayloadJson, new TypeReference<>() {}));
    } else {
      log.info("Ignored message of type " + messageType);
    }
  }

  public void notificationReceived(Message<NotificationCommand> message) {
    log.info("Like notification received");
    notificationService.create(message.getData());
    log.info("Correlated " + message);
  }

}
