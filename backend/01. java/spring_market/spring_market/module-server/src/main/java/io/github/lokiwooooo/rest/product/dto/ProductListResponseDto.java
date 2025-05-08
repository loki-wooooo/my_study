package io.github.lokiwooooo.rest.product.dto;

import io.github.lokiwooooo.util.dto.ResponsePageDto;
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
public class ProductListResponseDto extends ResponsePageDto {
    List<ProductResponseDto> productResponseDtoList;
}
