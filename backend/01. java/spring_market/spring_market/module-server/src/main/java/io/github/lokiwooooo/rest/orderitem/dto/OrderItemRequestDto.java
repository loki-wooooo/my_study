package io.github.lokiwooooo.rest.orderitem.dto;

import io.github.lokiwooooo.rest.order.dto.OrderRequestDto;
import io.github.lokiwooooo.rest.orderitemadditionalproduct.dto.OrderItemAdditionalProductRequestDto;
import io.github.lokiwooooo.rest.orderitemoption.dto.OrderItemOptionRequestDto;
import io.github.lokiwooooo.rest.product.dto.ProductRequestDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
public class OrderItemRequestDto {

    String id;
    String productId;
    String orderId;
    String name;
    String content;
    BigDecimal price;
    Integer quantity;

    OrderRequestDto orderRequestDto;
    ProductRequestDto productRequestDto;
    List<OrderItemOptionRequestDto> orderItemOptionRequestDtoList;
    List<OrderItemAdditionalProductRequestDto> orderItemAdditionalProductRequestDtoList;
}
