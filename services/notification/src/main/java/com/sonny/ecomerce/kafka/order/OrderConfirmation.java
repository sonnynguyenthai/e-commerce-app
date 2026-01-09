package com.sonny.ecomerce.kafka.order;

import com.sonny.ecomerce.kafka.payment.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResDTO customer,
        List<PurchaseRes> products

) {
}
