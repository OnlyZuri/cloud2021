package com.hjy.springcloud.controller;

import com.hjy.springcloud.entites.CommonResult;
import com.hjy.springcloud.entites.Payment;
import com.hjy.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private RestTemplate restTemplate;

    //private static String PAYMENT_URL_PREFIX = "http://localhost:8001/payment/";
    private static String PAYMENT_URL_PREFIX = "http://CLOUD-PAYMENT-SERVICE/payment/";

    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private LoadBalancer loadBalancer;

    @GetMapping("/getByMyRule/{id}")
    public CommonResult<Payment> getByMyRule(@PathVariable("id") Long id){
        List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        ServiceInstance selectedServiceInstance = loadBalancer.instances(serviceInstanceList);
        URI uri = selectedServiceInstance.getUri();
        return restTemplate.getForObject(uri + "/payment/get/" + id, CommonResult.class);
    }

    @GetMapping("/create")
    public CommonResult<Long> create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL_PREFIX + "create", payment, CommonResult.class);
    }

    @GetMapping("/createEntity")
    public CommonResult<Long> createEntity(Payment payment){
        ResponseEntity<CommonResult> entity =  restTemplate.postForEntity(PAYMENT_URL_PREFIX + "create", payment, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else{
            return new CommonResult<>(444, "error");
        }
    }

    @GetMapping("/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL_PREFIX + "get/" + id, CommonResult.class);
    }

    @GetMapping("/getEntity/{id}")
    public CommonResult<Payment> getPaymentEntityById(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL_PREFIX + "get/" + id, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else{
            return new CommonResult<>(444, "error");
        }
    }
}
