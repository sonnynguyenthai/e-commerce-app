package com.sonny.ecomerce.kafka.order;

public record CustomerResDTO(
        String id ,
        String firstName,
        String lastName,
        String email
) {
}
