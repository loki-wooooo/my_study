package io.github.lokiwooooo.rest.product.dto;

import io.github.lokiwooooo.rest.additionalproduct.dto.AdditionalProductResponseDto;
import io.github.lokiwooooo.rest.productoptiongroup.dto.ProductOptionGroupResponseDto;
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
public class ProductResponseDto {
    String id;
    String categoryId;
    String name;
    String content;
    BigDecimal price;
    Integer stock;

    // 상품 옵션
    List<ProductOptionGroupResponseDto> productOptionGroupResponseDtoList;

    // 추가 상품
    List<AdditionalProductResponseDto> additionalProductResponseDtoList;
}
