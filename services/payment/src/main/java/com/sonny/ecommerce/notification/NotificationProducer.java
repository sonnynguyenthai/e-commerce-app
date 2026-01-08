package com.sonny.ecommerce.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationProducer {
    private  final KafkaTemplate<String, PaymentNotificationReq> kafkaTemplate;
    public void send(PaymentNotificationReq paymentNotificationReq) {
        log.info("Sending payment notification request <{}>", paymentNotificationReq);
        Message<PaymentNotificationReq> message = MessageBuilder
                            .withPayload(paymentNotificationReq)
                            .setHeader(KafkaHeaders.TOPIC, "payment-topic")
                            .build();
        kafkaTemplate.send(message);
    }
}
