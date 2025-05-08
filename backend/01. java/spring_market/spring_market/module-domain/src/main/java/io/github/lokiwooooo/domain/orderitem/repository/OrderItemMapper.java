package io.github.lokiwooooo.domain.orderitem.repository;

import io.github.lokiwooooo.domain.order.entity.Order;
import io.github.lokiwooooo.domain.orderitem.dto.OrderItemDto;
import io.github.lokiwooooo.domain.orderitem.entity.OrderItem;
import io.github.lokiwooooo.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface OrderItemMapper extends GenericMapper<OrderItemDto, OrderItem> {

    @Mapping(target = "order", source = "orderId" , qualifiedByName = "idToOrder")
    @Mapping(target = "product", source = "productDto")
    OrderItem toEntity(OrderItemDto orderItemDto);

    @Named("idToOrder")
    default Order idToOrder(String orderId) {
        if (orderId == null) {
            return null;
        }

        Order order = new Order();
        order.setId(orderId);
        return order;
    }
}
