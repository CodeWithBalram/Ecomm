package com.ecom.productservice.service.Impl;

import com.ecom.productservice.dto.CategoryRequestDto;
import com.ecom.productservice.dto.CategoryResponseDto;
import com.ecom.productservice.dto.ExtendedCategoryResponseDto;
import com.ecom.productservice.entity.Category;
import com.ecom.productservice.entity.Product;
import com.ecom.productservice.mapper.CategoryMapper;
import com.ecom.productservice.mapper.ProductsMapper;
import com.ecom.productservice.repository.CategoryRepository;
import com.ecom.productservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService
{

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto) {
        Category category=new Category();
        category.setName(categoryRequestDto.getName());
        category.setDescription(categoryRequestDto.getDescription());
        Category savedCategory=categoryRepository.save(category);
        return CategoryMapper.toCategoryResponseDto(savedCategory);

    }

    @Override
    public ExtendedCategoryResponseDto getCategoryById(String categoryId)
    {
        Category category=categoryRepository.findById(categoryId)
                .orElseThrow(()->new RuntimeException("Resource not found"));
        return convertToExtendedCategoryResponseDto(category);
    }

    @Override
    public List<ExtendedCategoryResponseDto> getAllCategories() {
        List<Category> categoryList=categoryRepository.findAll();
        List<ExtendedCategoryResponseDto> categoryResponseDtos=new ArrayList<>();
        for(Category category :categoryList)
        {
            ExtendedCategoryResponseDto categoryResponseDto=convertToExtendedCategoryResponseDto(category);
            categoryResponseDtos.add(categoryResponseDto);
        }
        return categoryResponseDtos;
    }

    @Override
    public CategoryResponseDto updateCategory(String categoryId, CategoryRequestDto categoryRequestDto)
    {
        Category category=categoryRepository.findById(categoryId).orElseThrow(()->new RuntimeException("Resource bot found exception"));

        category.setName(categoryRequestDto.getName());
        category.setDescription(categoryRequestDto.getDescription());
        Category updateCategory=categoryRepository.save(category);
        return CategoryMapper.toCategoryResponseDto(updateCategory);
    }

    @Override
    public String deleteCategory(String categoryId)
    {
                 Category category= categoryRepository.findById(categoryId)
                         .orElseThrow(()->new RuntimeException("Resource not found exception"));

                categoryRepository.delete(category);
                 return "delete "+ categoryId + "Successfully";
    }

    private ExtendedCategoryResponseDto convertToExtendedCategoryResponseDto(Category category)
    {
        List<Product> productList=category.getProducts();


        return new ExtendedCategoryResponseDto(
                category.getCategoryId(),
                category.getName(),
                category.getDescription(),
                productList.stream().map(ProductsMapper::productResponseDto).toList()
        );
    }

}
