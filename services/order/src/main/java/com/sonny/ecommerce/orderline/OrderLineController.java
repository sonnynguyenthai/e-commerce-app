package com.sonny.ecommerce.orderline;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order-lines")
@RequiredArgsConstructor
public class OrderLineController {
    private final OrderLineService orderLineService;
    @GetMapping("/order/{order-id}")
    public ResponseEntity<List<OrderLineResDTO>> findById(@PathVariable("order-id") Integer orderId) {

    }
}
