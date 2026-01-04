package com.sonny.ecommerce.customer;

import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    public Customer toCustomer(CustomerReqDTO req) {
        if (req == null) {
            return null;
        }
      return Customer.builder()
              .id(req.id())
              .firstName(req.firstName())
              .lastName(req.lastName())
              .email(req.email())
              .address(req.address())
              .build();
    }

    public CustomerResDTO fromCustomer(Customer customer) {
        if (customer == null) {
            return null;
        }
        return new CustomerResDTO(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
}
