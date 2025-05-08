package io.github.lokiwooooo.rest.orderitem.dto;

import io.github.lokiwooooo.domain.orderitem.dto.OrderItemDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderItemRequestMapper {

    OrderItemDto toOrderItemDto(OrderItemRequestDto orderItemRequestDto);
}
