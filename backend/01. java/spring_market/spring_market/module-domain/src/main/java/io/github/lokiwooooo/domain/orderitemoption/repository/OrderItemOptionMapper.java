package io.github.lokiwooooo.domain.orderitemoption.repository;

import io.github.lokiwooooo.domain.orderitemoption.dto.OrderItemOptionDto;
import io.github.lokiwooooo.domain.orderitemoption.entity.OrderItemOption;
import io.github.lokiwooooo.mapper.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderItemOptionMapper extends GenericMapper<OrderItemOptionDto, OrderItemOption> {
}
