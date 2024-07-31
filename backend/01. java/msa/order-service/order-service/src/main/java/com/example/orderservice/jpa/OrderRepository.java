package com.example.orderservice.jpa;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderEntity, Long> {

    public OrderEntity findByOrderId(final String orderId) throws Exception;

    public Iterable<OrderEntity> findByUserId(final String userId) throws Exception;
}
