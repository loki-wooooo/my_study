package io.github.lokiwooooo.domain.notification.request.dto;

import io.github.lokiwooooo.domain.common.dto.CommonDto;
import io.github.lokiwooooo.domain.notification.request.entity.RequestChannel;
import io.github.lokiwooooo.domain.notification.request.entity.RequestStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
public class NotificationRequestDto extends CommonDto {
    UUID id;
    String clientType;
    RequestChannel requestChannel;
    String eventType;
    String message;
    LocalDateTime requestTime;
    LocalDateTime scheduledTime;
    RequestStatus requestStatus;
}
