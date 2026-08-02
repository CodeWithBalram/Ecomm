package com.ecom.payment_service.entity;

import com.ecom.payment_service.dto.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Entity
@Getter
@Setter
public class Payment
{
    @Id
    private String paymentId;
    private String customerId;
    private String orderId;
    private Double amount;
    private LocalDateTime paymentDate;
    private PaymentStatus paymentStatus;
    private String transactionId;
}
