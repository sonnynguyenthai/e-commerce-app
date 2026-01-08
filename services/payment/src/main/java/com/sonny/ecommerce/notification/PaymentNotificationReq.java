package com.sonny.ecommerce.notification;

import com.sonny.ecommerce.payment.PaymentMethod;

import java.math.BigDecimal;

public record PaymentNotificationReq(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstName,
        String customerLastName,
        String customerEmail
) {
}
