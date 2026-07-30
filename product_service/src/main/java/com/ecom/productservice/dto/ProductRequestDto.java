package com.ecom.productservice.dto;

import lombok.Data;

@Data
public class ProductRequestDto
{
    private String name;
    private String description;
    private Double price;
    private Integer stockQuantity;
    private String categoryId;
}
