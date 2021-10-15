package com.hjy.springcloud.service;

import com.hjy.springcloud.entites.Payment;

public interface PaymentService {
    Long create(Payment payment);

    Payment getPaymentById(Long id);
}
