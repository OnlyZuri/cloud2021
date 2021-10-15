package com.hjy.springcloud.controller;

import com.hjy.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @GetMapping("/get/hystrixok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        log.info("ok");
        return paymentService.paymentInfo_OK(id);
    }
}
