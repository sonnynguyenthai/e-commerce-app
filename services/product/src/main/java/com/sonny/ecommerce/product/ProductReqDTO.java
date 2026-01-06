package com.sonny.ecommerce.product;

import com.sonny.ecommerce.category.Category;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductReqDTO(
        Integer id,
        @NotNull(message = "Product name is require")
        String name,
        @NotNull(message = "Product description is require")
        String description,
        @Positive(message = "Product quantity must be positive")
        Double availableQuantity,
        @Positive(message = "Price quantity must be positive")
        BigDecimal price,
        @NotNull(message = "Category is required")
        Integer categoryId
) {

}
