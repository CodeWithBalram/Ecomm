package com.ecom.order_service.controller;

import com.ecom.order_service.dto.OrderRequestDto;
import com.ecom.order_service.dto.OrderResponseDto;
import com.ecom.order_service.dto.OrderStatus;
import com.ecom.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController
{
private final OrderService orderService;

@PostMapping
public ResponseEntity<?>createOrder(@RequestBody OrderRequestDto request)
{
    OrderResponseDto responseDto=orderService.placeOrder(request);
    return ResponseEntity.ok(responseDto);
}

@GetMapping("/{orderId}")
public ResponseEntity<?> getOrderById(@PathVariable String orderId)
{
    OrderResponseDto responseDto=orderService.getOrderById(orderId);
    return ResponseEntity.ok(responseDto);
}
@GetMapping("/customer/{customerId}")
public ResponseEntity<?> getOrderByCustomerId(@PathVariable String customerId)
{

    return ResponseEntity.ok(orderService.getOrdersByCustomerID(customerId));
}
@PatchMapping("/{orderId}/status")
public ResponseEntity<?>updateOrderService(@PathVariable String customerId, @RequestParam OrderStatus status)
{
   orderService.updateOrderStatus(customerId,status);
   return ResponseEntity.ok("order status updated to "+ status.name());
}

}
