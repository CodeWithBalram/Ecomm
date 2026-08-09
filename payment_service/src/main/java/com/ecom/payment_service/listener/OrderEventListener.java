package com.ecom.payment_service.listener;

import com.ecom.payment_service.dto.PaymentRequestDto;
import com.ecom.payment_service.events.OrderCreatedEvent;
import com.ecom.payment_service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class OrderEventListener
{
    private final PaymentService paymentService;

    @KafkaListener(topics = "order-events",groupId = "payment-service-group",containerFactory = "kafkaListenerContainerService")
    public void consumer(OrderCreatedEvent orderCreatedEvent)
    {
        try {
            log.info("ordercreated event received :{}", orderCreatedEvent);
            PaymentRequestDto paymentRequestDto = new PaymentRequestDto();
            paymentRequestDto.setOrderId(orderCreatedEvent.getOrderId());
            paymentRequestDto.setCustomerId(orderCreatedEvent.getCustomerId());
            paymentRequestDto.setAmount(Double.parseDouble(orderCreatedEvent.getTotalAmount()));
            paymentService.processPayment(paymentRequestDto);
        }
        catch(Exception e)
        {
            log.info("oder processing event orderCreatedEvent :{}",e.getMessage());
            throw new RuntimeException("oder processing event orderCreatedEvent :{}",e);
        }
    }
}
