package io.github.lokiwooooo.domain.orderitem.repository;

import io.github.lokiwooooo.domain.orderitem.dto.OrderItemDto;
import io.github.lokiwooooo.domain.orderitem.entity.OrderItem;
import io.github.lokiwooooo.mapper.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderItemMapper extends GenericMapper<OrderItemDto, OrderItem> {
}
