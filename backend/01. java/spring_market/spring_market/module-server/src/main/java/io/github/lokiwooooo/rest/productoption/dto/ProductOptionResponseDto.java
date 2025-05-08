package io.github.lokiwooooo.rest.productoption.dto;

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
public class ProductOptionResponseDto extends ResponseCommonDto {
    String id;
    BigDecimal price;
    String value;
    Integer stock;
}
