package io.github.lokiwooooo.rest.additionalproduct.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
public class AdditionalProductRequestDto {
    String id;
    String productId;
    String name;
    BigDecimal price;
    Integer stock;
}
