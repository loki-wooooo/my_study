package io.github.lokiwooooo;

import io.github.lokiwooooo.domain.notification.request.repository.NotificationRequestRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional(readOnly = true)
public class NotificationCleanupScheduler {

    @NonFinal
    @Value("${alert.front.delete-month}")
    Integer deleteMonth;

    NotificationRequestRepository notificationRequestRepository;

    /**
     * deleteMonth 에 따른 알림 데이터 삭제
     */
    @Scheduled(cron = "${batch.notification.delete-cron}")
    @Transactional
    public void deleteOldNotifications() {
        LocalDateTime deleteDateTime = LocalDateTime.now().minusMonths(deleteMonth);
        int deletedCount = notificationRequestRepository.deleteByRequestTimeBefore(deleteDateTime);
        log.info("### [Scheduler] {} 개월 초과 알림 데이터 삭제 시작: {}건###", deleteMonth, deletedCount);
    }
}
