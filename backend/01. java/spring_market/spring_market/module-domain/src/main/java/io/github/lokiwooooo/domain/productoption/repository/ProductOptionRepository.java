package io.github.lokiwooooo.domain.productoption.repository;

import io.github.lokiwooooo.domain.productoption.entity.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOptionRepository extends JpaRepository<ProductOption, String> {
}
