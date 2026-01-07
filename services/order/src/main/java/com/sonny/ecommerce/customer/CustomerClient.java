package com.sonny.ecommerce.customer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
       name = "customer-service",
        url = "/api/v1/customers"
)
public interface CustomerClient {
    @GetMapping("/{customer-id}")
    Optional<CustomerResDTO> findById(@PathVariable("customer-id") String customerId);
}
