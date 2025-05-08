package io.github.lokiwooooo.rest.orderitemadditionalproduct.dto;

import io.github.lokiwooooo.rest.orderitem.dto.OrderItemRequestDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
public class OrderItemAdditionalProductRequestDto {
    String id;
    BigDecimal price;
    Integer quantity;
    OrderItemRequestDto orderItemRequestDto;
}
