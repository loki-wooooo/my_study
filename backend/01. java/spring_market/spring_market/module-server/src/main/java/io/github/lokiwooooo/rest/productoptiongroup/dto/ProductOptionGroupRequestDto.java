package io.github.lokiwooooo.rest.productoptiongroup.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
public class ProductOptionGroupRequestDto {
    String id;
    String productId;
    String name;
}
