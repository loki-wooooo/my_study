package io.github.lokiwooooo.rest.order.dto;

import io.github.lokiwooooo.domain.order.entity.OrderStatus;
import io.github.lokiwooooo.util.dto.ResponseCommonDto;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
public class OrderResponseDto extends ResponseCommonDto {
    String id;
    LocalDateTime date;
    OrderStatus status;
    BigDecimal totalPrice;
    String address;
}
