package com.github.lukaslt1993.wallet.service;

import com.github.lukaslt1993.wallet.entity.Customer;
import com.github.lukaslt1993.wallet.entity.Wallet;
import com.github.lukaslt1993.wallet.exception.NotFoundException;
import com.github.lukaslt1993.wallet.repository.WalletRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Currency;

@Service
@AllArgsConstructor
public class WalletService {
    private final WalletRepository walletRepository;
    private final CustomerService customerService;

    public Wallet addWallet(Long customerId, String currencyCode) {
        Customer customer = customerService.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        Wallet wallet = new Wallet();
        wallet.setCustomer(customer);
        wallet.setCurrency(Currency.getInstance(currencyCode));
        wallet.setBalance(BigDecimal.ZERO);

        return walletRepository.save(wallet);
    }

    public Wallet findWalletById(Long walletId) {
        return walletRepository.findById(walletId)
                .orElseThrow(() -> new NotFoundException("Wallet not found for customer"));
    }
}
