package io.github.lokiwooooo.rest.product.web;

import io.github.lokiwooooo.annotaion.GetAnnotation;
import io.github.lokiwooooo.rest.product.dto.ProductListResponseDto;
import io.github.lokiwooooo.rest.product.dto.ProductRequestDto;
import io.github.lokiwooooo.rest.product.dto.ProductResponseDto;
import io.github.lokiwooooo.rest.product.service.ProductService;
import io.github.lokiwooooo.userdetail.CustomUserDetails;
import io.github.lokiwooooo.util.dto.ResponsePageDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "상품 관리")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
@RequestMapping(path = "/rest/market/v1/product")
public class ProductApiController {

    ProductService productService;

    @GetMapping("/category/{categoryId}")
    @GetAnnotation
    @Operation(summary = "카테고리별 상품 목록", description = "카테고리별 전체 상품 목록을 호출합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "요청 성공"),
            @ApiResponse(responseCode = "401", description = "인증 실패"),
            @ApiResponse(responseCode = "403", description = "인가 실패"),
            @ApiResponse(responseCode = "404", description = "페이지 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseEntity<ProductListResponseDto> findAllByCategoryId(
            @AuthenticationPrincipal final CustomUserDetails customUserDetails
            , @PathVariable final String categoryId
            , @ModelAttribute final ProductRequestDto productRequestDto
            , @Parameter(description = "페이지 정보",
                    schema = @Schema(implementation = ResponsePageDto.class),
                    example = "{\"page\": 0, \"size\": 10, \"sort\": [\"id,desc\"]}")
            @PageableDefault(page = 0, size = 10) @SortDefault(sort = "id", direction = Sort.Direction.DESC) final Pageable pageable
    ) {
        return ResponseEntity.ok(productService.findAllByCategoryId(customUserDetails, categoryId, productRequestDto, pageable));
    }

    @GetMapping("/{productId}")
    @GetAnnotation
    @Operation(summary = "상품 목록", description = "상품 상세 목록을 호출합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "요청 성공"),
            @ApiResponse(responseCode = "401", description = "인증 실패"),
            @ApiResponse(responseCode = "403", description = "인가 실패"),
            @ApiResponse(responseCode = "404", description = "페이지 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseEntity<ProductResponseDto> findById(
            @AuthenticationPrincipal final CustomUserDetails customUserDetails
            , @PathVariable final String productId
    ) {
        return ResponseEntity.ok(productService.findById(customUserDetails, productId));
    }
}
