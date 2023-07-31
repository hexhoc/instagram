package com.sm.socialmedia.dto.notification;

import java.util.UUID;

import com.sm.socialmedia.entity.NotificationTypeEnum;
import com.sm.socialmedia.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Notification command")
public class NotificationCommand {

    @Schema(description = "Recipient of the notification")
    private UUID userId;

    @Schema(description = "Notification type")
    private String type;

    @Schema(description = "Notification content")
    private String message;

}