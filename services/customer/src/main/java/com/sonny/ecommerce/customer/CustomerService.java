package com.sonny.ecommerce.customer;

import com.sonny.ecommerce.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.*;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private  final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    public String create(CustomerReqDTO req) {
        var customer = customerRepository.save(customerMapper.toCustomer(req));
        return customer.getId();
    }

    public void update(CustomerReqDTO req) {
        var customer = customerRepository.findById(req.id())
                .orElseThrow(() -> new CustomerNotFoundException(
                        format("Cannot update customer:: No customer found with the provided ID:: %s", req.id())
                ));
        mergerCustomer(customer, req);
        customerRepository.save(customer);
    }

    private void mergerCustomer(Customer customer, CustomerReqDTO req) {
        if(StringUtils.isNotBlank(req.firstName())) {
            customer.setFirstName(req.firstName());
        }
        if(StringUtils.isNotBlank(req.lastName())) {
            customer.setFirstName(req.lastName());
        }
        if(StringUtils.isNotBlank(req.email())) {
            customer.setLastName(req.email());
        }
        if(req.address() != null) {
            customer.setAddress(req.address());
        }

    }

    public @Nullable List<CustomerResDTO> findAll() {
        return customerRepository
                .findAll()
                .stream()
                .map(customerMapper::fromCustomer)
                .collect(Collectors.toList());
    }

    public @Nullable Boolean existById(String customerId) {
        return customerRepository.findById(customerId)
                .isPresent();
    }

    public CustomerResDTO findById(String customerId) {
        return customerRepository
                .findById(customerId)
                .map(customerMapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(format("Cannot find customer:: %s", customerId)));

    }

    public void deleteById(String customerId) {
         customerRepository.deleteById(customerId);
    }
}
