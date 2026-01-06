package com.sonny.ecommerce.product;


import jakarta.validation.constraints.NotNull;

public record ProductPurchaseReqDTO(
        @NotNull(message = "Product Id is required")
        Integer productId,
        @NotNull(message = "Product Id is required")
        Double quantity
) {
}
