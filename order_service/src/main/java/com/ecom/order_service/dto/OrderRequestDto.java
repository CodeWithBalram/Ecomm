package com.ecom.order_service.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDto
{
    private String customerId;
    private List<OrderItemRequestDto> items;

}
