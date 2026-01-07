package com.sonny.ecommerce.product;

import java.math.BigDecimal;

public record PurchaseRes(
        Integer productId,
        String name,
        String description,
        BigDecimal price,
        Double quantity
) {
}
