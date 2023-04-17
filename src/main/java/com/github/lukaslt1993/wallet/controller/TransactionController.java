package com.github.lukaslt1993.wallet.controller;

import com.github.lukaslt1993.wallet.dto.TransactionPageDTO;
import com.github.lukaslt1993.wallet.dto.WalletDTO;
import com.github.lukaslt1993.wallet.entity.Transaction;
import com.github.lukaslt1993.wallet.entity.Wallet;
import com.github.lukaslt1993.wallet.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/transactions/wallet/{walletId}")
@AllArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/credit")
    public ResponseEntity<WalletDTO> addFunds(@PathVariable Long walletId,
                                              @RequestParam BigDecimal amount) {
        Wallet wallet = transactionService.addFunds(walletId, amount);
        return ResponseEntity.ok(Wallet.toWalletDTO(wallet));
    }

    @PostMapping("/debit")
    public ResponseEntity<WalletDTO> withdrawFunds(@PathVariable Long walletId,
                                                   @RequestParam BigDecimal amount) {
        Wallet wallet = transactionService.withdrawFunds(walletId, amount);
        return ResponseEntity.ok(Wallet.toWalletDTO(wallet));
    }

    @GetMapping
    public ResponseEntity<TransactionPageDTO> getTransactions(@PathVariable Long walletId,
                                                              @RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "10") int size) {
        Page<Transaction> transactions = transactionService.getTransactions(walletId, page, size);
        TransactionPageDTO transactionPageDTO = Transaction.toTransactionPageDTO(transactions);
        return ResponseEntity.ok(transactionPageDTO);
    }
}
