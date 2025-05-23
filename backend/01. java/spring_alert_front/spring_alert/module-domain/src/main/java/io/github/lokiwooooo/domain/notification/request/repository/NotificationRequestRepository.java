package io.github.lokiwooooo.domain.notification.request.repository;

import io.github.lokiwooooo.domain.notification.request.entity.NotificationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface NotificationRequestRepository extends JpaRepository<NotificationRequest, UUID> {

    // 알림발송등록 특정 메시지 삭제
    @Query("DELETE FROM NotificationRequest n WHERE n.requestTime < :deleteDateTime")
    int deleteByRequestTimeBefore(@Param("deleteDateTime") final LocalDateTime deleteDateTime);
}
