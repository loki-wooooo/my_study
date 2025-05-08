package io.github.lokiwooooo.domain.product.dto;

import io.github.lokiwooooo.domain.additionalproduct.dto.AdditionalProductDto;
import io.github.lokiwooooo.domain.category.dto.CategoryDto;
import io.github.lokiwooooo.domain.common.dto.CommonDto;
import io.github.lokiwooooo.domain.productoptiongroup.dto.ProductOptionGroupDto;
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
public class ProductDto extends CommonDto implements Serializable {
    String id;
    String name;
    String content;
    BigDecimal price;
    Integer stock;
    CategoryDto categoryDto;
    List<ProductOptionGroupDto> productOptionGroupDtoList;
    List<AdditionalProductDto> additionalProductDtoList;
}
