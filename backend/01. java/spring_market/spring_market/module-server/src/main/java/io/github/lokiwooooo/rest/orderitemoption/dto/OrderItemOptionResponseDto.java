package io.github.lokiwooooo.rest.orderitemoption.dto;

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
public class OrderItemOptionResponseDto extends ResponseCommonDto {
    String id;
    String name;
    String value;
    BigDecimal additionalPrice;
}
