package com.ecom.productservice.mapper;

import com.ecom.productservice.dto.ProductResponseDto;
import com.ecom.productservice.entity.Product;

public class ProductsMapper
{
public static ProductResponseDto productResponseDto(Product product)
{
   ProductResponseDto productResponseDto=new ProductResponseDto();
   productResponseDto.setProductId(product.getProductId());
   productResponseDto.setName(product.getName());
   productResponseDto.setPrice(product.getPrice());
   productResponseDto.setDescription(product.getDescription());
   productResponseDto.setStockQuantity(product.getStockQuantity());
   productResponseDto.setInStock(product.getInStock());
   productResponseDto.setCategoryName(product.getCategory().getName());
   return productResponseDto;
}

public static Product toProductEntity(ProductResponseDto productResponseDto)
{
    Product product=new Product();
    product.setProductId(productResponseDto.getProductId());
    product.setName(productResponseDto.getName());
    product.setDescription(productResponseDto.getDescription());
    product.setPrice(productResponseDto.getPrice());
    product.setStockQuantity(productResponseDto.getStockQuantity());
    product.setInStock(productResponseDto.getInStock());
    return product;
}


}
