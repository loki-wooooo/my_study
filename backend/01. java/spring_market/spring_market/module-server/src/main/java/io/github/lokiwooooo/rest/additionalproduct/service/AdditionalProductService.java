package io.github.lokiwooooo.rest.additionalproduct.service;

import io.github.lokiwooooo.domain.additionalproduct.entity.AdditionalProduct;
import io.github.lokiwooooo.domain.additionalproduct.repository.AdditionalProductMapper;
import io.github.lokiwooooo.domain.additionalproduct.repository.AdditionalProductRepository;
import io.github.lokiwooooo.rest.additionalproduct.dto.AdditionalProductRequestDto;
import io.github.lokiwooooo.rest.additionalproduct.dto.AdditionalProductResponseDto;
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
public class AdditionalProductService {

    AdditionalProductRepository additionalProductRepository;
    AdditionalProductMapper additionalProductMapper;

    @Transactional
    public AdditionalProductResponseDto updateAdditionalProductStock(
            final CustomUserDetails customUserDetails
            , final AdditionalProductRequestDto additionalProductRequestDto
    ) {
        AdditionalProduct additionalProduct = additionalProductRepository.findById(additionalProductRequestDto.getId()).orElseThrow(() -> new IllegalArgumentException("추가 상품의 정보가 없습니다. additionalProductId: " + additionalProductRequestDto.getId()));
        additionalProduct.setStock(additionalProductRequestDto.getStock());
        additionalProductRepository.save(additionalProduct);
        return AdditionalProductResponseDto.builder()
                .id(additionalProduct.getId())
                .stock(additionalProduct.getStock())
                .build();
    }
}
