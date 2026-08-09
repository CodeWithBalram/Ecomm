package com.ecom.payment_service.service;

import com.ecom.payment_service.dto.PaymentRequestDto;
import com.ecom.payment_service.dto.PaymentResponseDto;
import com.ecom.payment_service.dto.PaymentStatus;
import com.ecom.payment_service.entity.Payment;
import com.ecom.payment_service.events.PaymentCompletedEvent;
import com.ecom.payment_service.mapper.PaymentMapper;
import com.ecom.payment_service.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService
{
    private final PaymentRepository paymentRepository;
    private final OrderClient orderClient;
    private final KafkaTemplate<String,PaymentCompletedEvent> kafkaTemplate;



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
//          orderClient.updateOrderStatus(paymentRequestDto.getOrderId(),"CONFIRMED");
        }
        else
        {
            payment.setPaymentStatus(PaymentStatus.FAILED);
            payment.setTransactionId("N/A");
         //   orderClient.updateOrderStatus(paymentRequestDto.getOrderId(),"CANCELLED");

        }
        Payment savePayment=paymentRepository.save(payment);

        String status=paymentSuccess ? "CONFIRMED":"CANCELLED";
        updatePaymentSuccess(paymentRequestDto.getOrderId(),status);
        return PaymentMapper.toPaymentResponseDto(savePayment);



    }

    public void updatePaymentSuccess(String orderId,String status)
    {
        try {
            PaymentCompletedEvent event = new PaymentCompletedEvent(orderId, status);
            log.info("Updating payment  status  for payment:{}",orderId);
            kafkaTemplate.send("payment-events", event);
            log.info("payment status update event sent to kafka for payment:{}",orderId);
        }
        catch(Exception e)
        {
            log.error("failed updated status for payment :{}",orderId);
            throw new RuntimeException("failed to update payment status",e);
        }
    }

    private String generatePaymentId()
    {
        return "pay-"+ UUID.randomUUID().toString();
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
