package com.ecom.productservice.service;

import com.ecom.productservice.dto.CategoryRequestDto;
import com.ecom.productservice.dto.CategoryResponseDto;
import com.ecom.productservice.dto.ExtendedCategoryResponseDto;

import java.util.List;

public interface  CategoryService
{
    CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto);
    List<ExtendedCategoryResponseDto> getAllCategories();
    CategoryResponseDto updateCategory(String categoryId,CategoryRequestDto categoryRequestDto);
    String deleteCategory(String categoryId);


    ExtendedCategoryResponseDto getCategoryById(String categoryId);
}
