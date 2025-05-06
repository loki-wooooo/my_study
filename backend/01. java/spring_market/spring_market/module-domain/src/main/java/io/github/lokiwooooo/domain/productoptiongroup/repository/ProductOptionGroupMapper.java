package io.github.lokiwooooo.domain.productoptiongroup.repository;

import io.github.lokiwooooo.domain.productoptiongroup.dto.ProductOptionGroupDto;
import io.github.lokiwooooo.domain.productoptiongroup.entity.ProductOptionGroup;
import io.github.lokiwooooo.mapper.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductOptionGroupMapper extends GenericMapper<ProductOptionGroupDto, ProductOptionGroup> {
}
