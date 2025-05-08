package io.github.lokiwooooo.rest.product.dto;

import io.github.lokiwooooo.domain.category.dto.CategoryDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
public class ProductRequestDto {
    String id;
    String name;
    String content;
    BigDecimal price;
    Integer stock;
    String categoryId;

    Boolean isUse;
}
