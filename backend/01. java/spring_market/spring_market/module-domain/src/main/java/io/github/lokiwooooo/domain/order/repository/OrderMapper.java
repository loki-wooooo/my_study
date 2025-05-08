package io.github.lokiwooooo.domain.order.repository;

import io.github.lokiwooooo.domain.order.dto.OrderDto;
import io.github.lokiwooooo.domain.order.entity.Order;
import io.github.lokiwooooo.domain.orderitem.dto.OrderItemDto;
import io.github.lokiwooooo.domain.orderitem.entity.OrderItem;
import io.github.lokiwooooo.domain.orderitemadditionalproduct.dto.OrderItemAdditionalProductDto;
import io.github.lokiwooooo.domain.orderitemadditionalproduct.entity.OrderItemAdditionalProduct;
import io.github.lokiwooooo.domain.orderitemoption.dto.OrderItemOptionDto;
import io.github.lokiwooooo.domain.orderitemoption.entity.OrderItemOption;
import io.github.lokiwooooo.domain.product.entity.Product;
import io.github.lokiwooooo.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface OrderMapper extends GenericMapper<OrderDto, Order> {

    @Mapping(source = "userDto", target = "user")
    @Mapping(source = "orderItemDtoList", target = "orderItemList")
    Order toEntity(OrderDto orderDto);

    @Mapping(source = "productId", target = "product", qualifiedByName = "idToProduct")
    @Mapping(source = "orderId", target = "order", qualifiedByName = "idToOrder")
    @Mapping(source = "orderItemOptionDtoList", target = "orderItemOptionList")
    @Mapping(source = "orderItemAdditionalProductDtoList", target = "orderItemAdditionalProductList")
    OrderItem toEntity(OrderItemDto orderItemDto);

    @Mapping(source = "orderItemId", target = "orderItem", qualifiedByName = "idToOrderItemId")
    OrderItemOption toEntity(OrderItemOptionDto orderItemOptionDto);

    @Mapping(source = "orderItemId", target = "orderItem", qualifiedByName = "idToOrderItemId")
    OrderItemAdditionalProduct toEntity(OrderItemAdditionalProductDto orderItemAdditionalProductDto);

    @Named("idToOrder")
    default Order idToOrder(String orderId) {
        if (orderId == null) {
            return null;
        }

        Order order = new Order();
        order.setId(orderId);
        return order;
    }

    @Named("idToOrderItemId")
    default OrderItem idToOrderItemId(String orderItemId) {
        if (orderItemId == null) {
            return null;
        }

        OrderItem orderItem = new OrderItem();
        orderItem.setId(orderItemId);
        return orderItem;
    }

    @Named("idToProduct")
    default Product idToProduct(String productId) {
        if (productId == null) {
            return null;
        }

        Product product = new Product();
        product.setId(productId);
        return product;
    }
}
