package com.hjy.springcloud.controller;

import com.hjy.springcloud.entites.CommonResult;
import com.hjy.springcloud.entites.Payment;
import com.hjy.springcloud.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderService orderService;

    @GetMapping("/getByFeign/{id}")
    public CommonResult<Payment> getByFeign(@PathVariable("id") Long id){
        return orderService.getPaymentById(id);
    }

}
