package com.sonny.ecommerce.orderline;

public record OrderLineReq(
        Integer id,
        Integer orderId,
        Integer productId,
        Double quantity
) {
}
