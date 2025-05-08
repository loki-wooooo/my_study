package io.github.lokiwooooo.rest.product.dto;

import io.github.lokiwooooo.domain.category.dto.CategoryDto;
import io.github.lokiwooooo.rest.additionalproduct.dto.AdditionalProductRequestDto;
import io.github.lokiwooooo.rest.productoptiongroup.dto.ProductOptionGroupRequestDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

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
    List<AdditionalProductRequestDto> additionalProductRequestDtoList;
    List<ProductOptionGroupRequestDto> productOptionGroupRequestDtoList;
}
