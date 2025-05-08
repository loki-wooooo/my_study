package io.github.lokiwooooo.rest.catogory.web;

import io.github.lokiwooooo.annotaion.DeleteAnnotation;
import io.github.lokiwooooo.annotaion.GetAnnotation;
import io.github.lokiwooooo.annotaion.PostAnnotation;
import io.github.lokiwooooo.annotaion.PutAnnotation;
import io.github.lokiwooooo.rest.catogory.dto.CategoryListResponseDto;
import io.github.lokiwooooo.rest.catogory.dto.CategoryRequestDto;
import io.github.lokiwooooo.rest.catogory.dto.CategoryResponseDto;
import io.github.lokiwooooo.rest.catogory.service.CategoryService;
import io.github.lokiwooooo.userdetail.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "카테고리 관리")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
@RequestMapping(path = "/rest/market/v1/categories")
public class CategoryApiController {

    CategoryService categoryService;

    @GetMapping
    @GetAnnotation
    @Operation(summary = "카테고리 목록", description = "전체 카테고리 목록을 호출합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "요청 성공"),
            @ApiResponse(responseCode = "401", description = "인증 실패"),
            @ApiResponse(responseCode = "403", description = "인가 실패"),
            @ApiResponse(responseCode = "404", description = "페이지 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseEntity<CategoryListResponseDto> findAll(
            @AuthenticationPrincipal final CustomUserDetails customUserDetails
            , @ModelAttribute final CategoryRequestDto categoryRequestDto
    ) {
        return ResponseEntity.ok(categoryService.findAll(customUserDetails, categoryRequestDto));
    }

    @GetMapping("/{id}")
    @GetAnnotation
    @Operation(summary = "카테고리 상세 목록", description = "전체 카테고리 상세 목록을 호출합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "요청 성공"),
            @ApiResponse(responseCode = "401", description = "인증 실패"),
            @ApiResponse(responseCode = "403", description = "인가 실패"),
            @ApiResponse(responseCode = "404", description = "페이지 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseEntity<CategoryResponseDto> findById(
            @AuthenticationPrincipal final CustomUserDetails customUserDetails
            , @PathVariable final String id
            , @Valid @ModelAttribute final CategoryRequestDto categoryRequestDto
    ) {
        return ResponseEntity.ok(categoryService.findById(customUserDetails, id, categoryRequestDto));
    }

    @PostMapping
    @PostAnnotation
    @Operation(summary = "카테고리 등록", description = "새로운 카테고리를 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "등록 성공"),
            @ApiResponse(responseCode = "401", description = "인증 실패"),
            @ApiResponse(responseCode = "403", description = "인가 실패"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseEntity<CategoryResponseDto> createCategory(
            @AuthenticationPrincipal final CustomUserDetails customUserDetails,
            @Valid @RequestBody final CategoryRequestDto categoryRequestDto
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(categoryService.create(customUserDetails, categoryRequestDto));
    }

    @PutMapping("/{id}")
    @PutAnnotation
    @Operation(summary = "카테고리 수정", description = "기존 카테고리 정보를 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "수정 성공"),
            @ApiResponse(responseCode = "401", description = "인증 실패"),
            @ApiResponse(responseCode = "403", description = "인가 실패"),
            @ApiResponse(responseCode = "404", description = "카테고리 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseEntity<CategoryResponseDto> updateCategory(
            @AuthenticationPrincipal final CustomUserDetails customUserDetails,
            @PathVariable final String id,
            @Valid @RequestBody final CategoryRequestDto categoryRequestDto
    ) {
        return ResponseEntity.ok(categoryService.update(customUserDetails, id, categoryRequestDto));
    }

    @DeleteMapping("/{id}")
    @DeleteAnnotation
    @Operation(summary = "카테고리 삭제", description = "카테고리를 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "삭제 성공"),
            @ApiResponse(responseCode = "401", description = "인증 실패"),
            @ApiResponse(responseCode = "403", description = "인가 실패"),
            @ApiResponse(responseCode = "404", description = "카테고리 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseEntity<Void> deleteCategory(
            @AuthenticationPrincipal final CustomUserDetails customUserDetails,
            @PathVariable final String id
    ) {
        categoryService.delete(customUserDetails, id);
        return ResponseEntity.noContent().build();
    }

}
