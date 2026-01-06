package com.sonny.ecommerce.product;

import com.sonny.ecommerce.category.Category;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public Product mapToProduct(ProductReqDTO req) {
        return Product.builder()
                .id(req.id())
                .name(req.name())
                .description(req.description())
                .price(req.price())
                .availableQuantity(req.availableQuantity())
                .category(Category
                        .builder()
                        .id(req.categoryId())
                        .build())
                .build();
    }

    public ProductResDTO mapToProductResponse( Product product) {
        return new ProductResDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription()
        );
    }

    public ProductPurchaseResDTO mapToProductPurchaseResponse(Product product, @NotNull(message = "Product Id is required") Double quantity) {
        return new ProductPurchaseResDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                quantity
        );
    }
}
