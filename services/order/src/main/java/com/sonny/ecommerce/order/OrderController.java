package com.sonny.ecommerce.order;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;
    @PostMapping
    public ResponseEntity<Integer> createOrder(@RequestBody OrderReqDTO req) {
        return  ResponseEntity.ok(orderService.create(req));
    }

    @GetMapping
    public ResponseEntity<List<OrderResDTO>> findAll() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/{order-id}")
    public ResponseEntity<OrderResDTO> findById(@PathVariable("order-id") Integer orderId) {
        return ResponseEntity.ok(orderService.findById(orderId));
    }
}
