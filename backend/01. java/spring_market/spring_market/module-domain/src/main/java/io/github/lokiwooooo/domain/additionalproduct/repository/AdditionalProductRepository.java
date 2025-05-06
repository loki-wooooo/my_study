package io.github.lokiwooooo.domain.additionalproduct.repository;

import io.github.lokiwooooo.domain.additionalproduct.entity.AdditionalProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdditionalProductRepository extends JpaRepository<AdditionalProduct, String> {
}
