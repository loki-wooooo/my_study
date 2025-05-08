package io.github.lokiwooooo.rest.order.dto;

import io.github.lokiwooooo.domain.order.entity.OrderStatus;
import io.github.lokiwooooo.domain.orderitem.dto.OrderItemDto;
import io.github.lokiwooooo.domain.user.dto.UserDto;
import io.github.lokiwooooo.rest.orderitem.dto.OrderItemRequestDto;
import io.github.lokiwooooo.rest.product.dto.ProductRequestDto;
import io.github.lokiwooooo.rest.user.dto.UserRequestDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
public class OrderRequestDto {
    String id;
    LocalDateTime date;
    OrderStatus status;
    BigDecimal totalPrice;
    String address;

    String productId;
    UserRequestDto userRequestDto;
    ProductRequestDto productRequestDto;
    List<OrderItemRequestDto> orderItemRequestDtoList;
}
