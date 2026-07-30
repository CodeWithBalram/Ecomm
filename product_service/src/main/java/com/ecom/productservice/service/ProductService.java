package com.ecom.productservice.service;

import com.ecom.productservice.dto.ProductRequestDto;
import com.ecom.productservice.dto.ProductResponseDto;

import java.util.List;

public interface ProductService
{
    ProductResponseDto createProduct(ProductRequestDto productRequestDto);
    ProductResponseDto getProductById(String productId);
    List<ProductResponseDto> getAllProduct();
    ProductResponseDto updateStockQuantity(String productId,Integer stockQuantity);
    String deleteProduct(String productId);
}
