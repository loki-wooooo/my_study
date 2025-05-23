package io.github.lokiwooooo.domain.notification.request.entity;

import io.github.lokiwooooo.domain.common.entity.CommonEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(
        name = "tb_notification_request",
        indexes = {
                @Index(name = "idx_client_type_request_time", columnList = "clientType, requestTime"),
                @Index(name = "idx_scheduled_time", columnList = "scheduledTime")
        }
)
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class NotificationRequest extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "BINARY(16)")
    @Comment("알림발송등록 PK")
    UUID id;

    @Column(name = "client_type", nullable = false, length = 32)
    @Enumerated(EnumType.STRING)
    @Comment("알림발송 타입(ex. SMS, E-MAIL, KAKAO_TALK")
    RequestType clientType;

    @Column(name = "event_type", nullable = false, length = 64)
    @Comment("알림발송 이벤트 타입(ex. ALERT, ALERT_HISTORY)")
    String eventType;

    @Column(name = "message", nullable = false, columnDefinition = "TEXT")
    @Comment("알림발송 메시지")
    String message;

    @Column(name = "request_time", nullable = false)
    @Comment("알림 발송 요청 시간")
    LocalDateTime requestTime;

    @Column(name = "scheduled_time", nullable = true)
    @Comment("알림 발송 예약 시간")
    LocalDateTime scheduledTime;

    @Column(name = "request_status", nullable = false, length = 16)
    @Enumerated(EnumType.STRING)
    @Comment("알림 발송 상태")
    RequestStatus requestStatus;
}
