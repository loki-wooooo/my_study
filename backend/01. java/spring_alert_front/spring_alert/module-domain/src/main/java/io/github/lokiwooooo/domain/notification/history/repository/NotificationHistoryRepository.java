package io.github.lokiwooooo.domain.notification.history.repository;

import io.github.lokiwooooo.domain.notification.history.entity.NotificationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NotificationHistoryRepository extends JpaRepository<NotificationHistory, UUID> {
}
