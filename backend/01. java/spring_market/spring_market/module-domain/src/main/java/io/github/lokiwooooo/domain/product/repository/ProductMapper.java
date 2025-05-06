package io.github.lokiwooooo.domain.product.repository;

import io.github.lokiwooooo.domain.product.dto.ProductDto;
import io.github.lokiwooooo.domain.product.entity.Product;
import io.github.lokiwooooo.mapper.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper extends GenericMapper<ProductDto, Product> {
}
