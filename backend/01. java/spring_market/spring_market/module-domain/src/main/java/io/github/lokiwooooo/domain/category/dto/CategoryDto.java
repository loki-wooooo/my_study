package io.github.lokiwooooo.domain.category.dto;

import io.github.lokiwooooo.domain.common.dto.CommonDto;
import io.github.lokiwooooo.domain.product.dto.ProductDto;
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
public class CategoryDto extends CommonDto implements Serializable {
    String id;
    String name;
    String level;
    CategoryDto parent;
    List<CategoryDto> children;
    List<ProductDto> productList;
}
