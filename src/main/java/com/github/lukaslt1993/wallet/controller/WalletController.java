package com.github.lukaslt1993.wallet.controller;

import com.github.lukaslt1993.wallet.dto.WalletDTO;
import com.github.lukaslt1993.wallet.entity.Wallet;
import com.github.lukaslt1993.wallet.service.WalletService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/api/wallets/customer/{customerId}")
@AllArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @PostMapping
    public ResponseEntity<WalletDTO> addWallet(@PathVariable Long customerId, @RequestParam String currency) {
        Wallet wallet = walletService.addWallet(customerId, currency);
        return ResponseEntity.ok(Wallet.toWalletDTO(wallet));
    }

}
