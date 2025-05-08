package io.github.lokiwooooo.rest.productoption.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
public class ProductOptionRequestDto {
    String id;
    BigDecimal price;
    String value;
    Integer stock;
}
