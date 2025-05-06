package io.github.lokiwooooo.domain.additionalproduct.repository;

import io.github.lokiwooooo.domain.additionalproduct.dto.AdditionalProductDto;
import io.github.lokiwooooo.domain.additionalproduct.entity.AdditionalProduct;
import io.github.lokiwooooo.mapper.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdditionalProductMapper extends GenericMapper<AdditionalProductDto, AdditionalProduct> {
}
