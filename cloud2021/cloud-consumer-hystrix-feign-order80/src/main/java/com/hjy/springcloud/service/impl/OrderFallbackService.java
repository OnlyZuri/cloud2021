package com.hjy.springcloud.service.impl;

import com.hjy.springcloud.service.OrderService;
import org.springframework.stereotype.Component;

@Component
public class OrderFallbackService implements OrderService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "ok fallback";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "nok fallback";
    }
}
