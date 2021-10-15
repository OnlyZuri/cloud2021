package com.hjy.springcloud.controller;

import com.hjy.springcloud.service.OrderService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
@Slf4j
//@DefaultProperties(defaultFallback = "defaultFallBackMethod")
public class OrderController {
    @Resource
    private OrderService orderService;

    @GetMapping("/getok/{id}")
    public String paymentInfo_OK(@PathVariable("id")Integer id){
        return orderService.paymentInfo_OK(id);
    }

    @GetMapping("/getnok/{id}")
//    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
//    })
//    @HystrixCommand
    String paymentInfo_TimeOut(@PathVariable("id")Integer id){
        return orderService.paymentInfo_TimeOut(id);
    }

    public String paymentInfo_TimeOutHandler(@PathVariable("id")Integer id){
        return "消费者端超时或异常";
    }

    public String defaultFallBackMethod(){
        return "默认超时或异常返回";
    }

}
