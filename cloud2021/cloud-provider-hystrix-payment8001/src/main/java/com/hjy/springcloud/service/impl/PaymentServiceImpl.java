package com.hjy.springcloud.service.impl;

import com.hjy.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    @Override
    @HystrixCommand(fallbackMethod = "paymentInfo_break", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
    })
    public String paymentInfo_OK(Integer id) {
        if (id < 0){
            throw new RuntimeException("id不能为负数");
        }
        return "线程池" + Thread.currentThread().getName() + " paymentInfo_OK, id:" + id;
    }

    public String paymentInfo_break(Integer id) {
        return "多次异常，触发熔断";
    }
}
