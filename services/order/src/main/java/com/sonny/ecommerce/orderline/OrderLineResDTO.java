package com.sonny.ecommerce.orderline;

public record OrderLineResDTO(
        Integer id,
        Double quantity,
        Integer productId,
        Integer orderId
) {
}
