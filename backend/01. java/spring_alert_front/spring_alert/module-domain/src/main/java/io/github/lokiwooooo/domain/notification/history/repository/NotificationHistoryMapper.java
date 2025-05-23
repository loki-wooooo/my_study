package io.github.lokiwooooo.domain.notification.history.repository;

import io.github.lokiwooooo.domain.notification.history.dto.NotificationHistoryDto;
import io.github.lokiwooooo.domain.notification.history.entity.NotificationHistory;
import io.github.lokiwooooo.mapper.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationHistoryMapper extends GenericMapper<NotificationHistoryDto, NotificationHistory> {
}
