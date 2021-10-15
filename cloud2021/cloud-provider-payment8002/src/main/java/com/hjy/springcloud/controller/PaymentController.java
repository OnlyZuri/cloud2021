package com.hjy.springcloud.controller;

import com.hjy.springcloud.entites.CommonResult;
import com.hjy.springcloud.entites.Payment;
import com.hjy.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String port;

    @PostMapping("/create")
    public CommonResult<Long> create(@RequestBody Payment payment){
        Long id = paymentService.create(payment);
        if (id != null){
            return new CommonResult<Long>(200, "success", payment.getId());
        }else{
            return new CommonResult<Long>(500, "create error");
        }
    }

    @GetMapping("/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment result = paymentService.getPaymentById(id);
        if (result != null){
            return new CommonResult<Payment>(200, "success, 8002", result);
        }else{
            return new CommonResult<Payment>(200, "no result");
        }
    }

    @GetMapping("/lb")
    public CommonResult<String> lb(){
        return new CommonResult<>(200, "success", port);
    }
}
