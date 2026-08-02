package com.ecom.payment_service.mapper;

import com.ecom.payment_service.dto.PaymentResponseDto;
import com.ecom.payment_service.entity.Payment;

public class PaymentMapper
{
    public static PaymentResponseDto toPaymentResponseDto(Payment payment)

    {
        PaymentResponseDto paymentResponseDto=new PaymentResponseDto();
        paymentResponseDto.setPaymentId(payment.getPaymentId());
        paymentResponseDto.setOrderId(payment.getOrderId());
        paymentResponseDto.setAmount(payment.getAmount());
        paymentResponseDto.setTransactionId(payment.getTransactionId());
        paymentResponseDto.setPaymentStatus(payment.getPaymentStatus());
        return paymentResponseDto;
    }

    public static Payment toPaymentEntity(PaymentResponseDto paymentResponseDto)

    {
        Payment payment=new Payment();
        paymentResponseDto.setCustomerId(paymentResponseDto.getCustomerId());
        paymentResponseDto.setOrderId(paymentResponseDto.getOrderId());
        paymentResponseDto.setAmount(paymentResponseDto.getAmount());
        paymentResponseDto.setTransactionId(paymentResponseDto.getTransactionId());
        paymentResponseDto.setPaymentStatus(paymentResponseDto.getPaymentStatus());
        return payment;
    }
}
