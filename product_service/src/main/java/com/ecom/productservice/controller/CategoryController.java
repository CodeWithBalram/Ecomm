package com.ecom.productservice.controller;

import com.ecom.productservice.dto.CategoryRequestDto;
import com.ecom.productservice.dto.CategoryResponseDto;
import com.ecom.productservice.dto.ExtendedCategoryResponseDto;
import com.ecom.productservice.service.CategoryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController
{
    private CategoryService categoryService;

    public CategoryController(  CategoryService categoryService)
    {
        this.categoryService=categoryService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public CategoryResponseDto createCategory(@RequestBody CategoryRequestDto categoryRequestDto)
    {
        return categoryService.createCategory(categoryRequestDto);
    }


    @GetMapping
    public List<ExtendedCategoryResponseDto> getAllCategories()
    {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{categoryId}")
    public ExtendedCategoryResponseDto getCategoryById(@PathVariable String categoryId)
    {
        return categoryService.getCategoryById(categoryId);
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/{categoryId}/update")
    public CategoryResponseDto updateCategory(@PathVariable String categoryId,@RequestBody CategoryRequestDto categoryRequestDto)
    {
        return categoryService.updateCategory(categoryId,categoryRequestDto);
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{categoryId}")
    public String deleteCategory(@PathVariable String categoryId)
    {
       return  categoryService.deleteCategory(categoryId);
    }




}
