package com.sonny.ecommerce.order;

import org.springframework.stereotype.Service;

@Service
public class OrderMapper {
    public Order mapToOrder(OrderReqDTO req) {
        return Order
                .builder()
                .id(req.id())
                .reference(req.reference())
                .totalAmount(req.amount())
                .paymentMethod(req.paymentMethod())
                .customerId(req.customerId())
                .build();
    }

    public OrderResDTO fromOrder(Order order) {
        return new OrderResDTO(
                order.getId(),
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getCustomerId()
        );
    }
}
