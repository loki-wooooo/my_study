package io.github.lokiwooooo.rest.product.dto;

import io.github.lokiwooooo.domain.product.dto.ProductDto;
import io.github.lokiwooooo.domain.productoption.dto.ProductOptionDto;
import io.github.lokiwooooo.domain.productoptiongroup.dto.ProductOptionGroupDto;
import io.github.lokiwooooo.rest.additionalproduct.dto.AdditionalProductResponseDto;
import io.github.lokiwooooo.rest.productoption.dto.ProductOptionResponseDto;
import io.github.lokiwooooo.rest.productoptiongroup.dto.ProductOptionGroupResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductResponseMapper {

    @Mapping(source = "productOptionGroupDtoList", target = "productOptionGroupResponseDtoList")
    @Mapping(source = "additionalProductDtoList", target = "additionalProductResponseDtoList")
    ProductResponseDto toResponseDto(ProductDto productDto);

    @Mapping(source = "productOptionDtoList", target = "productOptionResponseDtoList")
    ProductOptionGroupResponseDto toProductOptionGroupResponseDto(ProductOptionGroupDto productOptionGroupDto);

    ProductOptionResponseDto toProductOptionResponseDto(ProductOptionDto productOptionDto);

    AdditionalProductResponseDto toAdditionalProductResponseDto(ProductDto productDto);
}
