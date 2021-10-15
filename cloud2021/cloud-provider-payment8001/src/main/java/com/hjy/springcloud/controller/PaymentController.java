package com.hjy.springcloud.controller;

import com.hjy.springcloud.entites.CommonResult;
import com.hjy.springcloud.entites.Payment;
import com.hjy.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String port;

    @GetMapping("/discovery")
    public CommonResult<Object> discovery(){
        List<String> services = discoveryClient.getServices();
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        Map<String, Object> result = new HashMap<>();
        result.put("services", services);
        result.put("payment-instances", instances);
        return new CommonResult<>(200, "success", result);
    }

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
            return new CommonResult<Payment>(200, "success, 8001", result);
        }else{
            return new CommonResult<Payment>(200, "no result");
        }
    }

    @GetMapping("/lb")
    public CommonResult<String> lb(){
        return new CommonResult<>(200, "success", port);
    }
}
