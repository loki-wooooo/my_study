package io.github.lokiwooooo.domain.orderitemadditionalproduct.repository;

import io.github.lokiwooooo.domain.orderitemadditionalproduct.entity.OrderItemAdditionalProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemAdditionalProductRepository extends JpaRepository<OrderItemAdditionalProduct, String> {
}
