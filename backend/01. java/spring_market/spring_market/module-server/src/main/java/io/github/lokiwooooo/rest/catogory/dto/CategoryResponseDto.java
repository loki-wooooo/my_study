package io.github.lokiwooooo.rest.catogory.dto;

import io.github.lokiwooooo.util.dto.ResponseCommonDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
public class CategoryResponseDto extends ResponseCommonDto {
    String id;
    String name;
    String level;
    String parentId;
    CategoryResponseDto parent;
    List<CategoryResponseDto> children;
}
