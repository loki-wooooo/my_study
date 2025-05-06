package io.github.lokiwooooo.domain.order.repository;

import io.github.lokiwooooo.domain.order.dto.OrderDto;
import io.github.lokiwooooo.domain.order.entity.Order;
import io.github.lokiwooooo.mapper.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper extends GenericMapper<OrderDto, Order> {
}
