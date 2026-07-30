package com.ecom.order_service.dto;

import lombok.Data;

@Data
public class OrderItemRequestDto
{
    private String productId;
    private int quantity;

}
