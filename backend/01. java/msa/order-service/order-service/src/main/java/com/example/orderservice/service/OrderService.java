package com.example.orderservice.service;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.jpa.OrderEntity;

public interface OrderService {

    OrderDto createOrder(final OrderDto orderDetails) throws Exception;

    OrderDto getOrderByOrderId(final String orderId) throws Exception;

    Iterable<OrderEntity> getOrdersByUserId(final String userId) throws Exception;
}
