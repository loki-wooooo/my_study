package io.github.lokiwooooo.domain.order.dto;

import io.github.lokiwooooo.domain.common.dto.CommonDto;
import io.github.lokiwooooo.domain.order.entity.OrderStatus;
import io.github.lokiwooooo.domain.orderitem.dto.OrderItemDto;
import io.github.lokiwooooo.domain.user.dto.UserDto;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
public class OrderDto extends CommonDto implements Serializable {
    String id;
    LocalDateTime date;
    OrderStatus status;
    BigDecimal totalPrice;
    String address;
    UserDto userDto;
    List<OrderItemDto> orderItemDtoList;
}
