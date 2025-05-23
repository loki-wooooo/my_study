package io.github.lokiwooooo.domain.notification.request.repository;

import io.github.lokiwooooo.domain.notification.request.entity.NotificationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NotificationRequestRepository extends JpaRepository<NotificationRequest, UUID> {
}
