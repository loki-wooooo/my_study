package com.example.userservice.client;

import com.example.userservice.vo.ResponseOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

// microservice name으로 정의
@FeignClient(name = "order-service")
public interface OrderServiceClient {

    // 잘못된 값으로 변경 -> error 처리
    @GetMapping("/order-service/{userId}/orders_ng")
//    @GetMapping("/order-service/{userId}/orders")
    List<ResponseOrder> getOrders(
            @PathVariable final String userId
    ) throws Exception;
}
