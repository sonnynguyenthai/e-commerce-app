package com.sonny.ecommerce.kafka;

import com.sonny.ecommerce.customer.CustomerResDTO;
import com.sonny.ecommerce.order.PaymentMethod;
import com.sonny.ecommerce.product.PurchaseRes;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResDTO customer,
        List<PurchaseRes> product

) {
}
