package io.github.lokiwooooo.rest.catogory.dto;

import io.github.lokiwooooo.domain.category.dto.CategoryDto;
import io.github.lokiwooooo.util.dto.ResponseCommonDto;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
public class CategoryResponseDto extends ResponseCommonDto {
    String id;
    String name;
    String level;
    CategoryResponseDto parent;
}
