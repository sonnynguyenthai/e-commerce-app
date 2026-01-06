package com.sonny.ecommerce.product;

import com.sonny.ecommerce.exception.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    public @Nullable Integer createProduct(@Valid ProductReqDTO req) {
        var product = productMapper.mapToProduct(req);
        return productRepository.save(product).getId();
    }

    public List<ProductPurchaseResDTO> purchaseProducts(List<ProductPurchaseReqDTO> req) {
        var productIds = req
                .stream()
                .map(ProductPurchaseReqDTO::productId)
                .toList();
        var storedProducts = productRepository.findAllByIdInOrderById(productIds);
        if(productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("One or more products does not exists");
        }
        var storesRequest = req
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseReqDTO::productId))
                .toList();
        var purchasedProducts = new ArrayList<ProductPurchaseResDTO>();
        for(int i = 0; i < storedProducts.size(); i++) {
            var product = storedProducts.get(i);
            var productReq = storesRequest.get(i);
            if(product.getAvailableQuantity() < productReq.quantity()) {
                throw new ProductPurchaseException("Insufficient quantity for product with id " + product.getId());
            }
            product.setAvailableQuantity(product.getAvailableQuantity() - productReq.quantity());
            productRepository.save(product);
            purchasedProducts.add(productMapper.mapToProductPurchaseResponse(product, productReq.quantity()));

        }

        return purchasedProducts;
    }

    public @Nullable ProductResDTO findById(Integer productId) {
        return productRepository.findById(productId)
                .map(productMapper::mapToProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with the ID:: %s " + productId));
    }

    public @Nullable List<ProductResDTO> findAll() {
        return productRepository.findAll().stream().map(productMapper::mapToProductResponse).collect(Collectors.toList());
    }
}
