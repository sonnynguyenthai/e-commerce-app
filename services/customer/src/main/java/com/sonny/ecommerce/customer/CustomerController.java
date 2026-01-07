package com.sonny.ecommerce.customer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerReqDTO req) {
        return ResponseEntity.ok(customerService.create(req));
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomer(@RequestBody @Valid CustomerReqDTO req) {
        customerService.update(req);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerResDTO>> getCustomers() {
        return ResponseEntity.ok(customerService.findAll());
    }

    @GetMapping("/exists/{customer-id}")
    public ResponseEntity<Boolean> existById(@PathVariable("customer-id") String customerId) {
        return ResponseEntity.ok(customerService.existById(customerId));
    }

    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerResDTO> getCustomerById(@PathVariable("customer-id") String customerId) {
        return ResponseEntity.ok(customerService.findById(customerId));
    }

    @DeleteMapping("/{customer-id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("customer-id") String customerId) {
        customerService.deleteById(customerId);
        return ResponseEntity.accepted().build();
    }
}
