package com.ecom.payment_service.service;

import com.ecom.payment_service.dto.OrderStatusUpdateRequestDto;
import org.apache.catalina.connector.Request;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OrderClient
{
    private final RestTemplate restTemplate = new RestTemplate();




    public void updateOrderStatus(String orderId,String status)
    {
        String url="http://localhost:8081/orders/"+orderId+"/status?status="+status;
        OrderStatusUpdateRequestDto request=new OrderStatusUpdateRequestDto(orderId,status);

        HttpEntity<OrderStatusUpdateRequestDto> entity=new HttpEntity<>(request);
        ResponseEntity<Void> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                entity,
                Void.class
        );
    }

}
