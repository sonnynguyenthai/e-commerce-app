package com.sonny.ecomerce.notification;

import com.sonny.ecomerce.kafka.order.OrderConfirmation;
import com.sonny.ecomerce.kafka.payment.PaymentConfirmation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class Notification {
    @Id
    private String id;
    private NotificationType notificationType;
    private LocalDateTime timestamp;
    private OrderConfirmation orderConfirmation;
    private PaymentConfirmation paymentConfirmation;
}
