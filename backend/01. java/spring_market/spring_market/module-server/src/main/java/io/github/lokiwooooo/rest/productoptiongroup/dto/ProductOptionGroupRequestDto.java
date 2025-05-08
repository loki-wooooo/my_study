package io.github.lokiwooooo.rest.productoptiongroup.dto;

import io.github.lokiwooooo.rest.productoption.dto.ProductOptionRequestDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

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

    List<ProductOptionRequestDto> productOptionRequestDtoList;
}
