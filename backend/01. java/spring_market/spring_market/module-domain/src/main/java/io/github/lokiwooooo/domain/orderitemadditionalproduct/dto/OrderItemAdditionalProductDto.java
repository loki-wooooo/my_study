package io.github.lokiwooooo.domain.orderitemadditionalproduct.dto;

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
public class OrderItemAdditionalProductDto extends CommonDto implements Serializable {

    String id;
    String orderItemId;
    String name;
    BigDecimal price;
    Integer quantity;
    OrderItemDto orderItemDto;
}
