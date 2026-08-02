package com.ecom.payment_service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseDto
{
    private String paymentId;
    private String orderId;
    private String  customerId;
    private Double amount;
    private String paymentDate;
    private PaymentStatus paymentStatus;
    private String transactionId;
}
