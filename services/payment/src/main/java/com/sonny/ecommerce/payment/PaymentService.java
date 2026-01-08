package com.sonny.ecommerce.payment;

import com.sonny.ecommerce.notification.NotificationProducer;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final NotificationProducer notificationProducer;

    public  Integer save(@Valid PaymentReqDTO req) {
        var payment =  paymentRepository.save(paymentMapper.toPayment(req));
        notificationProducer.send(paymentMapper.toPaymentNotificationReq(payment, req));
        return payment.getId();
    }
}
