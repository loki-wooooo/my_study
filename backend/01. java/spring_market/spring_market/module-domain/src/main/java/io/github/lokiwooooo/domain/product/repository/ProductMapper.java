package io.github.lokiwooooo.domain.product.repository;

import io.github.lokiwooooo.domain.additionalproduct.dto.AdditionalProductDto;
import io.github.lokiwooooo.domain.additionalproduct.entity.AdditionalProduct;
import io.github.lokiwooooo.domain.product.dto.ProductDto;
import io.github.lokiwooooo.domain.product.entity.Product;
import io.github.lokiwooooo.domain.productoption.dto.ProductOptionDto;
import io.github.lokiwooooo.domain.productoption.entity.ProductOption;
import io.github.lokiwooooo.domain.productoptiongroup.dto.ProductOptionGroupDto;
import io.github.lokiwooooo.domain.productoptiongroup.entity.ProductOptionGroup;
import io.github.lokiwooooo.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper extends GenericMapper<ProductDto, Product> {

    @Mapping(source = "category", target = "categoryDto")
    @Mapping(source = "productOptionGroupList", target = "productOptionGroupDtoList")
    @Mapping(source = "additionalProductList", target = "additionalProductDtoList")
    ProductDto toDto(Product product);

    @Mapping(source = "productOptionList", target = "productOptionDtoList")
    ProductOptionGroupDto toDto(ProductOptionGroup productOptionGroup);

    ProductOptionDto toDto(ProductOption productOption);

    AdditionalProductDto toDto(AdditionalProduct additionalProduct);

    @Mapping(source = "categoryDto", target = "category")
    @Mapping(source = "productOptionGroupDtoList", target = "productOptionGroupList")
    @Mapping(source = "additionalProductDtoList", target = "additionalProductList")
    Product toEntity(ProductDto productDto);
}
