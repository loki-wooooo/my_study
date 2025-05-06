package io.github.lokiwooooo.domain.orderitemoption.repository;

import io.github.lokiwooooo.domain.orderitemoption.entity.OrderItemOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemOptionRepository extends JpaRepository<OrderItemOption, String> {
}
