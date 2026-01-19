package com.sonny.ecommerce.kafka.order;

public record CustomerResDTO(
        String id ,
        String firstName,
        String lastName,
        String email
) {
}
