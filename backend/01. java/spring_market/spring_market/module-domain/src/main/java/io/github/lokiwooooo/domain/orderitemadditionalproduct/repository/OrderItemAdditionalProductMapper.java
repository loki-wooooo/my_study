package io.github.lokiwooooo.domain.orderitemadditionalproduct.repository;

import io.github.lokiwooooo.domain.orderitemadditionalproduct.dto.OrderItemAdditionalProductDto;
import io.github.lokiwooooo.domain.orderitemadditionalproduct.entity.OrderItemAdditionalProduct;
import io.github.lokiwooooo.mapper.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderItemAdditionalProductMapper extends GenericMapper<OrderItemAdditionalProductDto, OrderItemAdditionalProduct> {
}
