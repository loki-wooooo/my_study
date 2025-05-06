package io.github.lokiwooooo.domain.category.repository;

import io.github.lokiwooooo.domain.category.dto.CategoryDto;
import io.github.lokiwooooo.domain.category.entity.Category;
import io.github.lokiwooooo.mapper.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends GenericMapper<CategoryDto, Category> {
}
