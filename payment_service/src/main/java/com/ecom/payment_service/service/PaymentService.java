package com.ecom.payment_service.service;

import com.ecom.payment_service.dto.PaymentRequestDto;
import com.ecom.payment_service.dto.PaymentResponseDto;
import com.ecom.payment_service.dto.PaymentStatus;
import com.ecom.payment_service.entity.Payment;
import com.ecom.payment_service.mapper.PaymentMapper;
import com.ecom.payment_service.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService
{
    private  PaymentRepository paymentRepository;
    private  OrderClient orderClient;

    PaymentService( PaymentRepository paymentRepository,OrderClient orderClient)
    {
        this.paymentRepository=paymentRepository;
        this.orderClient=orderClient;
    }

    public PaymentResponseDto processPayment(PaymentRequestDto paymentRequestDto)
    {
        String paymentId=generatePaymentId();

        Payment payment=new Payment();
        payment.setPaymentId(paymentId);
        payment.setOrderId(paymentRequestDto.getOrderId());
        payment.setCustomerId(paymentRequestDto.getCustomerId());
        payment.setAmount(paymentRequestDto.getAmount());
        payment.setPaymentDate(LocalDateTime.now());
        boolean paymentSuccess=new Random().nextBoolean();
        if(paymentSuccess)
        {
            payment.setPaymentStatus(PaymentStatus.SUCCESS);
            payment.setTransactionId(UUID.randomUUID().toString().substring(0,8));
            orderClient.updateOrderStatus(paymentRequestDto.getOrderId(),"CONFIRMED");
        }
        else
        {
            payment.setPaymentStatus(PaymentStatus.FAILED);
            payment.setTransactionId("N/A");
            orderClient.updateOrderStatus(paymentRequestDto.getOrderId(),"CANCELLED");

        }
        Payment savePayment=paymentRepository.save(payment);
        return PaymentMapper.toPaymentResponseDto(savePayment);



    }

    private String generatePaymentId()
    {
        return "pay-"+ UUID.randomUUID().toString().substring(0,8);
    }

    public PaymentResponseDto getPaymentByOrderId(String orderId)
    {
        Payment payment=paymentRepository.findByOrderId(orderId);
        if(payment==null)
        {
            return null;
        }
        return PaymentMapper.toPaymentResponseDto(payment);
    }
}
