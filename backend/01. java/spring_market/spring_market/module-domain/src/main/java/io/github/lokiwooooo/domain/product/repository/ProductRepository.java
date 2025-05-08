package io.github.lokiwooooo.domain.product.repository;

import io.github.lokiwooooo.domain.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    Page<Product> findAllByCategoryIdAndIsUse(final String categoryId, final Boolean isUse, Pageable pageable);

    Optional<Product> findByIdAndIsUse(final String id, final Boolean isUse);

}
