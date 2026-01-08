package com.sonny.ecommerce.payment;

import com.sonny.ecommerce.notification.PaymentNotificationReq;
import lombok.Data;
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

    public PaymentNotificationReq toPaymentNotificationReq(Payment payment, PaymentReqDTO req) {
        return new PaymentNotificationReq(
                payment.getId(),
                req.orderReference(),
                payment.getAmount(),
                payment.getPaymentMethod(),
                req.customer().firstName(),
                req.customer().lastName(),
                req.customer().email()
        );
    }
}
