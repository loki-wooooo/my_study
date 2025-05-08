package io.github.lokiwooooo.rest.productoption.service;

import io.github.lokiwooooo.domain.productoption.entity.ProductOption;
import io.github.lokiwooooo.domain.productoption.repository.ProductOptionMapper;
import io.github.lokiwooooo.domain.productoption.repository.ProductOptionRepository;
import io.github.lokiwooooo.rest.productoption.dto.ProductOptionRequestDto;
import io.github.lokiwooooo.rest.productoption.dto.ProductOptionResponseDto;
import io.github.lokiwooooo.userdetail.CustomUserDetails;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional(readOnly = true)
@Slf4j
public class ProductOptionService {

    ProductOptionRepository productOptionRepository;
    ProductOptionMapper productOptionMapper;

    @Transactional
    public ProductOptionResponseDto updateProductOptionStock(
            final CustomUserDetails customUserDetails
            , final ProductOptionRequestDto productOptionRequestDto
            ) {
        ProductOption productOption = productOptionRepository.findById(productOptionRequestDto.getId()).orElseThrow(() -> new IllegalArgumentException("상품 옵션의 정보가 없습니다. 상품 옵션 ID: " + productOptionRequestDto.getId()));

        productOption.setStock(productOptionRequestDto.getStock());
        productOptionRepository.save(productOption);

        return ProductOptionResponseDto.builder()
                .id(productOption.getId())
                .stock(productOption.getStock())
                .build();
    }
}
