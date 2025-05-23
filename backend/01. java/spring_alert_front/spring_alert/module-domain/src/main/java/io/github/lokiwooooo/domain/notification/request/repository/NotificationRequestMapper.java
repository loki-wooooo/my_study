package io.github.lokiwooooo.domain.notification.request.repository;

import io.github.lokiwooooo.domain.notification.request.dto.NotificationRequestDto;
import io.github.lokiwooooo.domain.notification.request.entity.NotificationRequest;
import io.github.lokiwooooo.mapper.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationRequestMapper extends GenericMapper<NotificationRequestDto, NotificationRequest> {
}
