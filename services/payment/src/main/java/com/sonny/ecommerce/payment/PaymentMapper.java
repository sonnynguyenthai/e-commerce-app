package com.sonny.ecommerce.payment;

import com.sonny.ecommerce.notification.PaymentConfirmation;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {
    public Payment toPayment(PaymentReqDTO req) {
        return Payment
                .builder()
                .id(req.id())
                .amount(req.amount())
                .paymentMethod(req.paymentMethod())
                .orderId(req.orderId())
                .build();
    }

    public PaymentConfirmation toPaymentConfirmation(Payment payment, PaymentReqDTO req) {
        return new PaymentConfirmation(
                req.orderReference(),
                payment.getAmount(),
                payment.getPaymentMethod(),
                req.customer().firstName(),
                req.customer().lastName(),
                req.customer().email()
        );
    }
}
