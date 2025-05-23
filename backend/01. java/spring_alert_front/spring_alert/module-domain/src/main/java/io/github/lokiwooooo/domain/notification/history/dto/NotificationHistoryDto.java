package io.github.lokiwooooo.domain.notification.history.dto;

import io.github.lokiwooooo.domain.common.dto.CommonDto;
import io.github.lokiwooooo.domain.notification.history.entity.HistoryStatus;
import io.github.lokiwooooo.domain.notification.request.entity.NotificationRequest;
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
public class NotificationHistoryDto extends CommonDto {
    UUID id;
    NotificationRequest notificationRequest;
    LocalDateTime sentTime;
    HistoryStatus historyStatus;
    String errorMessage;
}
