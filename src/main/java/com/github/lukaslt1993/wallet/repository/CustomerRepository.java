package com.github.lukaslt1993.wallet.repository;

import com.github.lukaslt1993.wallet.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

}

