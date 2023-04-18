package com.github.lukaslt1993.wallet.service;

import com.github.lukaslt1993.wallet.entity.Customer;
import com.github.lukaslt1993.wallet.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {
    private CustomerRepository customerRepository;

    public Customer findById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
    }
}
