package com.sonny.ecommerce.orderline;

import com.sonny.ecommerce.order.Order;
import com.sonny.ecommerce.order.OrderResDTO;

public class OrderLineMapper {
    public OrderLine mapToOrder(OrderLineReq orderLineReq) {
        return OrderLine
                .builder()
                .productId(orderLineReq.productId())
                .quantity(orderLineReq.quantity())
                .order(Order.builder().id(orderLineReq.orderId()).build())
                .build();
    }

    public OrderLineResDTO fromOrder(OrderLine orderLine) {
        return new OrderLineResDTO(
                orderLine.getId(),
                orderLine.getQuantity(),
                orderLine.getProductId(),
                orderLine.getOrder().getId()
        );
    }
}
