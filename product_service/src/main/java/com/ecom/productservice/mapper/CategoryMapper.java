package com.ecom.productservice.mapper;

import com.ecom.productservice.dto.CategoryResponseDto;
import com.ecom.productservice.entity.Category;

public class CategoryMapper
{
    public static CategoryResponseDto toCategoryResponseDto(Category category)
    {
        CategoryResponseDto categoryResponseDto=new CategoryResponseDto();
        categoryResponseDto.setCategoryId(category.getCategoryId());
        categoryResponseDto.setName(category.getName());
        categoryResponseDto.setDescription(category.getDescription());
        return categoryResponseDto;
    }

    public static Category toCategoryEntity(CategoryResponseDto categoryResponseDto)
    {
        Category category =new Category();
        category.setCategoryId(categoryResponseDto.getCategoryId());
        category.setName(categoryResponseDto.getName());
        category.setDescription(categoryResponseDto.getDescription());
        return category;
    }

}
