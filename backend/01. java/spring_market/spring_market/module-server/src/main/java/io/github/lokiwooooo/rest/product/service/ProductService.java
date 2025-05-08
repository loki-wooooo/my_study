package io.github.lokiwooooo.rest.product.service;

import io.github.lokiwooooo.domain.product.dto.ProductDto;
import io.github.lokiwooooo.domain.product.entity.Product;
import io.github.lokiwooooo.domain.product.repository.ProductMapper;
import io.github.lokiwooooo.domain.product.repository.ProductRepository;
import io.github.lokiwooooo.rest.product.dto.ProductListResponseDto;
import io.github.lokiwooooo.rest.product.dto.ProductRequestDto;
import io.github.lokiwooooo.rest.product.dto.ProductResponseDto;
import io.github.lokiwooooo.rest.product.dto.ProductResponseMapper;
import io.github.lokiwooooo.userdetail.CustomUserDetails;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional(readOnly = true)
@Slf4j
public class ProductService {

    ProductRepository productRepository;
    ProductMapper productMapper;
    ProductResponseMapper productResponseMapper;

    public ProductListResponseDto findAllByCategoryId(
            final CustomUserDetails customUserDetails
            , final String categoryId
            , final ProductRequestDto productRequestDto
            , final Pageable pageable
    ) {

        // 상품 목록 데이터 호출
        Page<Product> findAllByCategoryPageList = productRepository.findAllByCategoryIdAndIsUse(categoryId, productRequestDto.getIsUse(), pageable);

        // 응답 데이터 생성
        List<ProductResponseDto> productResponseDtoList = findAllByCategoryPageList.getContent().stream()
                .map(product -> ProductResponseDto.builder()
                        .id(product.getId().toString())
                        .categoryId(product.getCategory().getId())
                        .name(product.getName())
                        .content(product.getContent())
                        .price(product.getPrice())
                        .stock(product.getStock())
                        .build())
                .toList();

        return ProductListResponseDto
                .builder()
                .productResponseDtoList(productResponseDtoList)
                .pageNumber(findAllByCategoryPageList.getNumber())
                .pageSize(findAllByCategoryPageList.getSize())
                .totalPages(findAllByCategoryPageList.getTotalPages())
                .totalElements(findAllByCategoryPageList.getTotalElements())
                .first(findAllByCategoryPageList.isFirst())
                .last(findAllByCategoryPageList.isLast())
                .build();
    }

    public ProductResponseDto findById(
            final CustomUserDetails customUserDetails
            , final String id
    ) {
        // 상품 상세 목록 데이터 호출
        Product product = productRepository.findByIdAndIsUse(id, true)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다. ID: " + id));

        ProductDto productDto = productMapper.toDto(product);
        return productResponseMapper.toResponseDto(productDto);
    }

    //재고수정
    @Transactional
    public ProductResponseDto updateStock(final CustomUserDetails customUserDetails, final ProductRequestDto productRequestDto) {

        Product product = productRepository.findById(productRequestDto.getId()).orElseThrow(() -> new IllegalArgumentException("상품ID를 확인해 주세요: " + productRequestDto.getId()));
        product.setStock(productRequestDto.getStock());
        productRepository.save(product);

        return ProductResponseDto.builder()
                .id(product.getId().toString())
                .stock(product.getStock())
                .build();
    }

    // 생성, 수정, 삭제는 나중에

}
