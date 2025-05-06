package io.github.lokiwooooo.domain.additionalproduct.dto;

import io.github.lokiwooooo.domain.common.dto.CommonDto;
import io.github.lokiwooooo.domain.product.dto.ProductDto;
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
public class AdditionalProductDto extends CommonDto implements Serializable {

    String id;
    BigDecimal price;
    Integer stock;
    ProductDto productDto;
}
