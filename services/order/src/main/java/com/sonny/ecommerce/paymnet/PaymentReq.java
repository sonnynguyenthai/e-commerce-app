package com.sonny.ecommerce.paymnet;

import com.sonny.ecommerce.customer.CustomerResDTO;
import com.sonny.ecommerce.order.PaymentMethod;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.math.BigDecimal;

public record PaymentReq(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResDTO customer
) {
}
