package io.github.lokiwooooo.domain.productoptiongroup.dto;

import io.github.lokiwooooo.domain.common.dto.CommonDto;
import io.github.lokiwooooo.domain.product.dto.ProductDto;
import io.github.lokiwooooo.domain.productoption.dto.ProductOptionDto;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
public class ProductOptionGroupDto extends CommonDto implements Serializable {
    String id;
    String name;
    String productId;
    ProductDto productDto;
    List<ProductOptionDto> productOptionDtoList;
}
