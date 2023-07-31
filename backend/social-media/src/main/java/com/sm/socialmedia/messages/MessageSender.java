package com.sm.socialmedia.messages;

import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sm.socialmedia.config.KafkaConfig;
import com.sm.socialmedia.dto.notification.NotificationCommand;
import com.sm.socialmedia.entity.Comment;
import com.sm.socialmedia.entity.NotificationTypeEnum;
import com.sm.socialmedia.entity.Post;
import com.sm.socialmedia.entity.PostUserLike;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * Helper to send messages, currently nailed to Kafka, but could also send via AMQP (e.g. RabbitMQ) or
 * any other transport easily
 */
@Component
@RequiredArgsConstructor
@Log4j2
public class MessageSender {

  private final KafkaTemplate<String, String> kafkaTemplate;
  private final ObjectMapper objectMapper;

  public void sendLikePostNotification(PostUserLike likedPost) {
    var message = String.format("The user %s liked your post %s",
                                likedPost.getUser().getUsername(),
                                likedPost.getPost().getTitle());
    send(
            new Message<>(
                    "LikeNotificationCommand",
                    UUID.randomUUID().toString(),
                    NotificationCommand.builder()
                                       .userId(likedPost.getPost().getUserId())
                                       .type(NotificationTypeEnum.LIKE.name())
                                       .message(message)
                                       .build()
            ),
            KafkaConfig.NOTIFICATION_TOPIC);
  }

  public void sendCommentNotification(Comment comment) {
    var message = String.format("The user %s commented your post %s",
                                comment.getUser().getUsername(),
                                comment.getPost().getTitle());
    send(
            new Message<>(
                    "CommentNotificationCommand",
                    UUID.randomUUID().toString(),
                    NotificationCommand.builder()
                                       .userId(comment.getPost().getUserId())
                                       .type(NotificationTypeEnum.COMMENT.name())
                                       .message(message)
                                       .build()
            ),
            KafkaConfig.NOTIFICATION_TOPIC);
  }

  public void send(Message<?> m, String topicName) {
    try {
      // avoid too much magic and transform ourselves
      String jsonMessage = objectMapper.writeValueAsString(m);
      
      // wrap into a proper message for Kafka including a header
      ProducerRecord<String, String> record = new ProducerRecord<String, String>(topicName, jsonMessage);
      record.headers().add("type", m.getType().getBytes());

      // and send it
      var future = kafkaTemplate.send(record);
      future.thenApply(result -> {
        log.info("Message [{}] delivered with offset {}",
                 record,
                 result.getRecordMetadata().offset());
        return result;
      }).exceptionally(ex -> {
        log.warn("Unable to deliver message [{}]. {}",
                 record,
                 ex.getMessage());
        return null;
      });

    } catch (Exception e) {
      throw new RuntimeException("Could not transform and send message: "+ e.getMessage(), e);
    }
  }
}
