package com.ecom.productservice.service.Impl;

import com.ecom.productservice.dto.ProductRequestDto;
import com.ecom.productservice.dto.ProductResponseDto;
import com.ecom.productservice.entity.Category;
import com.ecom.productservice.entity.Product;
import com.ecom.productservice.repository.CategoryRepository;
import com.ecom.productservice.repository.ProductRepository;
import com.ecom.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ecom.productservice.mapper.ProductsMapper.productResponseDto;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService
{
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto)
    {   Category category= categoryRepository.findById(productRequestDto.getCategoryId())
            .orElseThrow(()->new RuntimeException("Category not found"));
        Product product=new Product();
        product.setName(productRequestDto.getName());
        product.setPrice(productRequestDto.getPrice());
        product.setDescription(productRequestDto.getDescription());
        product.setStockQuantity(productRequestDto.getStockQuantity());
        product.setCategory(category);
        Product saveProduct=productRepository.save(product);
        return productResponseDto(saveProduct);


    }

    @Override
    public ProductResponseDto getProductById(String productId)
    {
        Product product=productRepository.findById(productId)
                .orElseThrow(()->new RuntimeException("product not found"));
        return productResponseDto(product);
    }

    @Override
    public List<ProductResponseDto> getAllProduct() {
        return productRepository.findAll().stream()
                .map(product -> productResponseDto(product))
                .toList();

    }

    @Override
    public ProductResponseDto updateStockQuantity(String productId, Integer stockQuantity)
    {
        Product product=productRepository.findById(productId)
                .orElseThrow(()->new RuntimeException("product not found"));
            product.setStockQuantity(product.getStockQuantity()+ stockQuantity);
                    productRepository.save(product);
                    return productResponseDto(product);

    }

    @Override
    public String deleteProduct(String productId) {
         Product product=productRepository.findById(productId)
                 .orElseThrow(()->new RuntimeException("produc not found"));
         productRepository.delete(product);
         return "product"+productId+"deleted Successfully ";
    }
}
