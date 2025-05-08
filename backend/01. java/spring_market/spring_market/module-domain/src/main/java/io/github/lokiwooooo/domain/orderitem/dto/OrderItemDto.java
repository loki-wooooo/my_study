package io.github.lokiwooooo.domain.orderitem.dto;

import io.github.lokiwooooo.domain.common.dto.CommonDto;
import io.github.lokiwooooo.domain.order.dto.OrderDto;
import io.github.lokiwooooo.domain.orderitemadditionalproduct.dto.OrderItemAdditionalProductDto;
import io.github.lokiwooooo.domain.orderitemoption.dto.OrderItemOptionDto;
import io.github.lokiwooooo.domain.product.dto.ProductDto;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
public class OrderItemDto extends CommonDto implements Serializable {
    String id;
    String name;
    String content;
    BigDecimal price;
    Integer quantity;
//    OrderDto orderDto;
    String orderId;
    String productId;
    ProductDto productDto;
    List<OrderItemOptionDto> orderItemOptionDtoList;
    List<OrderItemAdditionalProductDto> orderItemAdditionalProductDtoList;
}
