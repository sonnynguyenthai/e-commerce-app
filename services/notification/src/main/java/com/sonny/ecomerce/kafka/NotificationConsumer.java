package com.sonny.ecomerce.kafka;

import com.sonny.ecomerce.kafka.order.OrderConfirmation;
import com.sonny.ecomerce.kafka.payment.PaymentConfirmation;
import com.sonny.ecomerce.notification.Notification;
import com.sonny.ecomerce.notification.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.sonny.ecomerce.notification.NotificationType.ORDER_CONFIRMATION;
import static com.sonny.ecomerce.notification.NotificationType.PAYMENT_CONFIRMATION;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {
    private final NotificationRepository notificationRepository;
    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) {
        log.info("Received Payment Confirmation from payment-topic: {}", paymentConfirmation);
        notificationRepository.save(
                Notification.builder()
                        .notificationType(PAYMENT_CONFIRMATION)
                        .timestamp(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                    .build()
        );

        //send email
    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderSuccessNotification(OrderConfirmation orderConfirmation) {
        log.info("Received Order Confirmation from payment-topic: {}", orderConfirmation);
        notificationRepository.save(
                Notification.builder()
                        .notificationType(ORDER_CONFIRMATION)
                        .timestamp(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );

        //send email
    }
}
