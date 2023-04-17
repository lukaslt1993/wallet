package com.github.lukaslt1993.wallet.repository;

import com.github.lukaslt1993.wallet.entity.Wallet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends CrudRepository<Wallet, Long> {

}
