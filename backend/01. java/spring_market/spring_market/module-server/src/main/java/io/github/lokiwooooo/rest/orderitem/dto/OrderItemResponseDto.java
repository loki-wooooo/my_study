package io.github.lokiwooooo.rest.orderitem.dto;

import io.github.lokiwooooo.util.dto.ResponseCommonDto;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
public class OrderItemResponseDto extends ResponseCommonDto {
    String id;
    String name;
    String content;
    BigDecimal price;
    Integer quantity;
}
