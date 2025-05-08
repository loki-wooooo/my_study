package io.github.lokiwooooo.domain.category.repository;

import io.github.lokiwooooo.domain.category.dto.CategoryDto;
import io.github.lokiwooooo.domain.category.entity.Category;
import io.github.lokiwooooo.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends GenericMapper<CategoryDto, Category> {

    // 무한참조로인한 pk 값으로 리턴
    @Mapping(source = "parent.id", target = "parentId")
    CategoryDto toDto(Category category);
}
