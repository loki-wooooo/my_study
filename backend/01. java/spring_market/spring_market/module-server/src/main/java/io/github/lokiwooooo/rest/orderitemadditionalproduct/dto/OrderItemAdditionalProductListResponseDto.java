package io.github.lokiwooooo.rest.orderitemadditionalproduct.dto;

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
public class OrderItemAdditionalProductListResponseDto extends ResponseCommonDto {
    List<OrderItemAdditionalProductResponseDto> orderItemAdditionalProductResponseDtoList;
}
