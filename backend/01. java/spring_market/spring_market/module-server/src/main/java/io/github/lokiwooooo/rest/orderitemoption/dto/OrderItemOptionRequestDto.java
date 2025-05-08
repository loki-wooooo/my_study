package io.github.lokiwooooo.rest.orderitemoption.dto;

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
public class OrderItemOptionRequestDto {
    String id;
    String name;
    String value;
    BigDecimal additionalPrice;
    OrderItemRequestDto orderItemRequestDto;
}
