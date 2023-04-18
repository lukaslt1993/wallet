package com.github.lukaslt1993.wallet.controller;

import com.github.lukaslt1993.wallet.dto.CustomerDTO;
import com.github.lukaslt1993.wallet.entity.Customer;
import com.github.lukaslt1993.wallet.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers/{customerId}")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable Long customerId) {
        Customer customer = customerService.findById(customerId);
        return ResponseEntity.ok(Customer.toCustomerDTO(customer));
    }
}
