package com.hjy.springcloud.dao;

import com.hjy.springcloud.entites.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {
    Long create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);
}
