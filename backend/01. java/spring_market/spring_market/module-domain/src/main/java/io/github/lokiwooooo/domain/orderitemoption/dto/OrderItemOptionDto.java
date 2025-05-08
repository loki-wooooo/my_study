package io.github.lokiwooooo.domain.orderitemoption.dto;

import io.github.lokiwooooo.domain.common.dto.CommonDto;
import io.github.lokiwooooo.domain.orderitem.dto.OrderItemDto;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
public class OrderItemOptionDto extends CommonDto implements Serializable {
    String id;
    String orderItemId;
    String name;
    String value;
    BigDecimal additionalPrice;
    OrderItemDto orderItemDto;
}
