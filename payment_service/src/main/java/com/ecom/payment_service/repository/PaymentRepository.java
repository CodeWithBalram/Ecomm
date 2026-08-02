package com.ecom.payment_service.repository;

import com.ecom.payment_service.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,String>
{

    Payment findByOrderId(String orderId);
}
