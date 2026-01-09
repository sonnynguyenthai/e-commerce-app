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
    private  final KafkaTemplate<String, PaymentConfirmation> kafkaTemplate;
    public void send(PaymentConfirmation paymentConfirmation) {
        log.info("Sending payment notification request <{}>", paymentConfirmation);
        Message<PaymentConfirmation> message = MessageBuilder
                            .withPayload(paymentConfirmation)
                            .setHeader(KafkaHeaders.TOPIC, "payment-topic")
                            .build();
        kafkaTemplate.send(message);
    }
}
