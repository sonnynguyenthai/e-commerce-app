package com.sonny.ecommerce.product;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(("/api/v1/products"))
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Integer> createProduct(@RequestBody @Valid ProductReqDTO req) {
        return ResponseEntity.ok(productService.createProduct(req));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResDTO>> purchaseProducts(
            @RequestBody List<ProductPurchaseReqDTO> req
    ) {
        return ResponseEntity.ok(productService.purchaseProducts(req));
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResDTO> getProductById(@PathVariable("product-id") Integer productId) {
        return ResponseEntity.ok(productService.findById(productId));
    }

    @GetMapping
    public ResponseEntity<List<ProductResDTO>> getProducts() {
        return ResponseEntity.ok(productService.findAll());
    }

}
