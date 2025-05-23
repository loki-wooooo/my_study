package io.github.lokiwooooo.domain.notification.history.entity;

import io.github.lokiwooooo.domain.common.entity.CommonEntity;
import io.github.lokiwooooo.domain.notification.request.entity.NotificationRequest;
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
        name = "tb_notification_history",
        indexes = {
                @Index(name = "idx_notification_request_id", columnList = "notificationRequestId")
        }
)
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class NotificationHistory extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "BINARY(16)")
    @Comment("알림내역조회 PK")
    UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notification_request_id", nullable = false)
    @Comment("알림발송등록 PK")
    NotificationRequest notificationRequest;

    @Column(name = "sent_time", nullable = false)
    @Comment("보낸 시간")
    LocalDateTime sentTime;

    @Column(name = "history_status", nullable = false, length = 16)
    @Comment("내역 상태")
    HistoryStatus historyStatus;

    @Column(name = "error_message", columnDefinition = "TEXT", nullable = true)
    @Comment("실패 메시지")
    String errorMessage;
}
