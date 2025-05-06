package io.github.lokiwooooo.domain.productoption.repository;

import io.github.lokiwooooo.domain.productoption.dto.ProductOptionDto;
import io.github.lokiwooooo.domain.productoption.entity.ProductOption;
import io.github.lokiwooooo.mapper.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductOptionMapper extends GenericMapper<ProductOptionDto, ProductOption> {
}
