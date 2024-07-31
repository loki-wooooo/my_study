package com.example.orderservice.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ResponseOrder {

    private String productId;
    private String orderId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;
    private Date createdAt;


}
