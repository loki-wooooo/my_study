package io.github.lokiwooooo.rest.catogory.service;

import io.github.lokiwooooo.domain.category.dto.CategoryDto;
import io.github.lokiwooooo.domain.category.entity.Category;
import io.github.lokiwooooo.domain.category.repository.CategoryMapper;
import io.github.lokiwooooo.domain.category.repository.CategoryRepository;
import io.github.lokiwooooo.hierarchy.HierarchyBuilder;
import io.github.lokiwooooo.rest.catogory.dto.CategoryListResponseDto;
import io.github.lokiwooooo.rest.catogory.dto.CategoryRequestDto;
import io.github.lokiwooooo.rest.catogory.dto.CategoryResponseDto;
import io.github.lokiwooooo.rest.catogory.dto.CategoryResponseMapper;
import io.github.lokiwooooo.userdetail.CustomUserDetails;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional(readOnly = true)
@Slf4j
public class CategoryService {

    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;

    CategoryResponseMapper categoryResponseMapper;

    HierarchyBuilder hierarchyBuilder;

    public CategoryListResponseDto findAll(
            final CustomUserDetails customUserDetails
            , final CategoryRequestDto categoryRequestDto
    ) {

        // level로 정렬해서 데이터를 갖고옴
        List<Category> categoryList = categoryRepository.findAllByIsUseOrderByLevel(categoryRequestDto.getIsUse());

        // level에 따라 그룹핑
        Map<Integer, List<CategoryDto>> categoryDtoByLevel = categoryList.stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.groupingBy(dto -> Integer.parseInt(dto.getLevel())));

        // level_1에 따른 계층 데이터 갖고옴
        List<CategoryDto> rootCategories = categoryDtoByLevel.get(1).stream()
                .map(categoryDto -> hierarchyBuilder.buildHierarchy(categoryDto, categoryDtoByLevel, 4))
                .toList();

        // dto to response dto
        List<CategoryResponseDto> responseList =
                categoryResponseMapper.toResponseDtoList(rootCategories);

        // return data
        return CategoryListResponseDto.builder()
                .categoryResponseDtoList(responseList)
                .build();
    }

    public CategoryResponseDto findById(
            final CustomUserDetails customUserDetails
            , final String id
            , final CategoryRequestDto categoryRequestDto
    ) {

        // id로 데이터 갖고옴
        Category category = categoryRepository.findByIdAndIsUse(id, categoryRequestDto.getIsUse()).orElseThrow(() -> new IllegalArgumentException("카테고리를 찾을 수 없습니다. ID: " + id));

        // 전체 데이터 갖고옴
        List<Category> categoryList = categoryRepository.findAllByIsUseOrderByLevel(categoryRequestDto.getIsUse());

        // level에 따라 그룹핑
        Map<Integer, List<CategoryDto>> categoryDtoByLevel = categoryList.stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.groupingBy(dto -> Integer.parseInt(dto.getLevel())));

        // level id에 따라 계층 데이터 조회
        CategoryDto categoryDto = categoryMapper.toDto(category);
        CategoryDto categoryDtoById = hierarchyBuilder.buildHierarchy(categoryDto, categoryDtoByLevel, 4);
        return categoryResponseMapper.toResponseDto(categoryDtoById);
    }

    @Transactional
    public CategoryResponseDto create(
            final CustomUserDetails customUserDetails
            , @Valid final CategoryRequestDto categoryRequestDto
    ) {

        // 부모 카테고리 존재 확인
        Category parentCategory = categoryRepository.findById(categoryRequestDto.getParentId()).orElse(null);
        CategoryDto parentCategoryDto = null;
        if (parentCategory != null) {
            parentCategory = categoryRepository.findById(categoryRequestDto.getParentId())
                    .orElseThrow(() -> new IllegalArgumentException("상위 카테고리를 찾을 수 없습니다."));
            parentCategoryDto = categoryMapper.toDto(parentCategory);
        }

        // 카테고리 생성
        Category category = Category.builder()
                .name(categoryRequestDto.getName())
                .level(Integer.valueOf(categoryRequestDto.getLevel()))
                .parent(parentCategory)
                .isUse(true)
                .build();
        Category createCategory = categoryRepository.save(category);

        return CategoryResponseDto.builder()
                .httpStatus(HttpStatus.CREATED)
                .id(createCategory.getId())
                .level(createCategory.getLevel().toString())
                .name(createCategory.getName())
                .build();
    }

    @Transactional
    public CategoryResponseDto update(
            final CustomUserDetails customUserDetails
            , final String id
            , @Valid final CategoryRequestDto categoryRequestDto) {

        // 카테고리 조회
        Category category = categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("카테고리를 찾을 수 없습니다. ID: " + id));

        category.setName(categoryRequestDto.getName());
        Category updateCategory = categoryRepository.save(category);

        return CategoryResponseDto.builder()
                .httpStatus(HttpStatus.OK)
                .id(updateCategory.getId())
                .level(updateCategory.getLevel().toString())
                .build();
    }

    @Transactional
    public void delete(
            final CustomUserDetails customUserDetails
            , final String id) {
        categoryRepository.deleteById(id);
    }
}
