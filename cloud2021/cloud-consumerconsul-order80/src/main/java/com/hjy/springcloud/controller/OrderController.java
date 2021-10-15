package com.hjy.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {
    @Resource
    private RestTemplate restTemplate;

    private static String PAYMENT_URL_PREFIX = "http://cloud-providerconsul-payment";

    @GetMapping("/consul")
    public String consul(){
        return restTemplate.getForObject(PAYMENT_URL_PREFIX + "/payment/consul", String.class);
    }

}
