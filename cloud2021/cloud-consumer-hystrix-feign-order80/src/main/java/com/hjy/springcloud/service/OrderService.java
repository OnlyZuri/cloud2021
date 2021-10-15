package com.hjy.springcloud.service;

import com.hjy.springcloud.service.impl.OrderFallbackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient(value = "CLOUD-PAYMENT-HYSTRIX-SERVICE", fallback = OrderFallbackService.class)
public interface OrderService {
    @GetMapping("/payment/get/hystrixok/{id}")
    String paymentInfo_OK(@PathVariable("id")Integer id);

    @GetMapping("/payment/get/hystrixnok/{id}")
    String paymentInfo_TimeOut(@PathVariable("id")Integer id);
}
