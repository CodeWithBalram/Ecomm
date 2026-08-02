package com.ecom.payment_service.cotroller;

import com.ecom.payment_service.dto.PaymentRequestDto;
import com.ecom.payment_service.dto.PaymentResponseDto;
import com.ecom.payment_service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController
{
    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentResponseDto> processPayment(@RequestBody PaymentRequestDto paymentRequestDto)
    {
        PaymentResponseDto response=paymentService.processPayment(paymentRequestDto);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/order/{orderId}")
    public ResponseEntity<PaymentResponseDto> getPaymentDetails(@PathVariable String orderId)
    {
           return ResponseEntity.ok(paymentService.getPaymentByOrderId(orderId));
    }

}
