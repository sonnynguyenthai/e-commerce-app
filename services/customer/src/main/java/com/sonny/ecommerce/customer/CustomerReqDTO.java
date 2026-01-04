package com.sonny.ecommerce.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerReqDTO(
        String id ,
        @NotNull(message = "Customer first name is not null")
        String firstName,
        @NotNull(message = "Customer last name is not null")
        String lastName,
        @NotNull(message = "Customer email is not null")
        @Email(message = "Customer email is not a valid email address")
        String email,
        Address address
) {

}
