package com.ecom.order_service.service;

import com.ecom.order_service.dto.ProductResponseDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductClient {

    private final RestTemplate restTemplate;

    public ProductClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ProductResponseDto getProductName(String productId) {
        String url = "http://product-service/products/" + productId;
        return restTemplate.getForObject(url, ProductResponseDto.class);
    }

    public void updateStock(String productId, int quantity) {

        String url = "http://product-service/products/"
                + productId
                + "/stock?stockQuantity="
                + quantity;

        HttpEntity<Integer> request = new HttpEntity<>(quantity);

        restTemplate.exchange(
                url,
                HttpMethod.PATCH,
                request,
                Void.class
        );
    }
}