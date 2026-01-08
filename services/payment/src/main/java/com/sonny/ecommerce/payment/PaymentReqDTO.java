package com.sonny.ecommerce.payment;

import com.sonny.ecommerce.customer.Customer;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.math.BigDecimal;

public record PaymentReqDTO(
         Integer id,
        BigDecimal amount,
         PaymentMethod paymentMethod,
         Integer orderId,
        String orderReference,
        Customer customer
) {
}
