package com.ecom.productservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class ExtendedCategoryResponseDto extends CategoryResponseDto
{
    List<ProductResponseDto> products;

    public ExtendedCategoryResponseDto(String categoryId,String name,String description,List<ProductResponseDto> products)
    {
        super(categoryId,name,description);
        this.products=products;
    }
}
