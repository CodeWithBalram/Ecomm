package com.ecom.productservice.controller;

import com.ecom.productservice.dto.ProductRequestDto;
import com.ecom.productservice.dto.ProductResponseDto;
import com.ecom.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController
{
    private final ProductService productService;
    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_SELLER', 'ROLE_ADMIN')")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto productRequestDto)
    {
       return productService.createProduct(productRequestDto);
    }

    @GetMapping("/{productId}")
    public ProductResponseDto getProductById(@PathVariable String productId)
    {
        return productService.getProductById(productId);
    }

    @GetMapping
    public List<ProductResponseDto> getAllProduct()
    {
        return productService.getAllProduct();
    }

    @PatchMapping("/{productId}/stock")
    @PreAuthorize("hasAnyAuthority('ROLE_SELLER', 'ROLE_ADMIN')")
    public ProductResponseDto updateStock(@PathVariable String productId,@RequestParam Integer stockQuantity)
   {
       return productService.updateStockQuantity(productId,stockQuantity);
   }


   @DeleteMapping("/{productId}")
   @PreAuthorize("hasAuthority('ROLE_SELLER')")
   public String deleteProduct(@PathVariable String productId)
   {
      return  productService.deleteProduct(productId);
   }


}
