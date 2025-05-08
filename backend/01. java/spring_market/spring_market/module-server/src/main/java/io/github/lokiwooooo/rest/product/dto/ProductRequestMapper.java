package io.github.lokiwooooo.rest.product.dto;

import io.github.lokiwooooo.domain.additionalproduct.dto.AdditionalProductDto;
import io.github.lokiwooooo.domain.product.dto.ProductDto;
import io.github.lokiwooooo.domain.productoption.dto.ProductOptionDto;
import io.github.lokiwooooo.domain.productoptiongroup.dto.ProductOptionGroupDto;
import io.github.lokiwooooo.rest.additionalproduct.dto.AdditionalProductRequestDto;
import io.github.lokiwooooo.rest.additionalproduct.dto.AdditionalProductResponseDto;
import io.github.lokiwooooo.rest.productoption.dto.ProductOptionRequestDto;
import io.github.lokiwooooo.rest.productoptiongroup.dto.ProductOptionGroupRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductRequestMapper {

    @Mapping(source = "productOptionGroupRequestDtoList", target = "productOptionGroupDtoList")
    @Mapping(source = "additionalProductRequestDtoList", target = "additionalProductDtoList")
    ProductDto toProductDto(ProductRequestDto productRequestDto);


    @Mapping(source = "productOptionRequestDtoList", target = "productOptionDtoList")
    ProductOptionGroupDto toProductOptionGroupDto(ProductOptionGroupRequestDto productOptionGroupRequestDto);

    ProductOptionDto toProductOptionDto(ProductOptionRequestDto productOptionRequestDto);

    AdditionalProductDto toAdditionalProductDto(AdditionalProductRequestDto additionalProductRequestDto);
}
