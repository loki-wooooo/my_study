package io.github.lokiwooooo.rest.catogory.dto;

import io.github.lokiwooooo.domain.category.dto.CategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryResponseMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "level", target = "level")
    @Mapping(source = "parent", target = "parent")
    @Mapping(source = "parentId", target = "parentId")
    @Mapping(source = "children", target = "children")
    @Mapping(target = "httpStatus", ignore = true)
    CategoryResponseDto toResponseDto(final CategoryDto categoryDto);

    List<CategoryResponseDto> toResponseDtoList(final List<CategoryDto> categoryDtoList);
}
