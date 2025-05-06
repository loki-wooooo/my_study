package io.github.lokiwooooo.domain.productoption.dto;

import io.github.lokiwooooo.domain.common.dto.CommonDto;
import io.github.lokiwooooo.domain.productoptiongroup.dto.ProductOptionGroupDto;
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
public class ProductOptionDto extends CommonDto implements Serializable {
    String id;
    BigDecimal additionalPrice;
    String value;
    Integer stock;
    ProductOptionGroupDto productOptionGroupDto;
}
