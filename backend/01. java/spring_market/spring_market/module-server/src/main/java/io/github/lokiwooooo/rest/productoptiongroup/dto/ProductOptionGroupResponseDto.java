package io.github.lokiwooooo.rest.productoptiongroup.dto;

import io.github.lokiwooooo.rest.productoption.dto.ProductOptionResponseDto;
import io.github.lokiwooooo.util.dto.ResponseCommonDto;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
public class ProductOptionGroupResponseDto extends ResponseCommonDto {
    String id;
    String productId;
    String name;
    List<ProductOptionResponseDto> productOptionResponseDtoList;
}
