package com.github.lukaslt1993.wallet.repository;

import com.github.lukaslt1993.wallet.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    Page<Transaction> findByWalletId(Long walletId, Pageable pageable);
}

