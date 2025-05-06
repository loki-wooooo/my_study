package io.github.lokiwooooo.domain.productoptiongroup.repository;

import io.github.lokiwooooo.domain.productoptiongroup.entity.ProductOptionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOptionGroupRepository extends JpaRepository<ProductOptionGroup, String> {
}
