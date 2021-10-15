package com.hjy.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/order")
public class OrderController {
    @Resource
    private RestTemplate restTemplate;

    public static String PAYMENT_URL_PREFIX = "http://cloud-payment-service";

    @GetMapping("/zk")
    public String orderZk(){
        return restTemplate.getForObject(PAYMENT_URL_PREFIX + "/payment/zk", String.class);
    }
}
