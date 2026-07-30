package com.ecom.productservice.config;

public class IdGenerator
{
    private static int productIdCounter;
    private static int categoryIdCounter;

    public static synchronized int getNextCategoryId()
    {
        categoryIdCounter++;
        return categoryIdCounter;
    }

    public static synchronized int getNextProductId()
    {
        productIdCounter++;
        return productIdCounter;
    }

}
