package com.sonny.ecommerce.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerResDTO(
        String id ,
        String firstName,
        String lastName,
        String email,
        Address address
) {

}
