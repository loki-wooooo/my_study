package io.github.lokiwooooo.domain.category.repository;

import io.github.lokiwooooo.domain.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

    // 사용여부 별 전체 목록 호출
    List<Category> findAllByIsUseOrderByLevel(final Boolean isUse);

    // 사용여부 및 ID 별 목록 호출
    Optional<Category> findByIdAndIsUse(final String id, final Boolean isUse);
}
