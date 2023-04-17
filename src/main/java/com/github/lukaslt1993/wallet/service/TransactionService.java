package com.github.lukaslt1993.wallet.service;

import com.github.lukaslt1993.wallet.config.AppConfig;
import com.github.lukaslt1993.wallet.entity.Transaction;
import com.github.lukaslt1993.wallet.entity.Wallet;
import com.github.lukaslt1993.wallet.enums.TransactionType;
import com.github.lukaslt1993.wallet.exception.InsufficientFundsException;
import com.github.lukaslt1993.wallet.exception.InvalidAmountException;
import com.github.lukaslt1993.wallet.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final WalletService walletService;
    private final AppConfig appConfig;

    public Transaction createCreditTransaction(Wallet wallet, BigDecimal amount) {
        Transaction transaction = new Transaction();
        transaction.setWallet(wallet);
        transaction.setAmount(amount);
        transaction.setType(TransactionType.CREDIT);
        return transactionRepository.save(transaction);
    }

    public Transaction createDebitTransaction(Wallet wallet, BigDecimal amount) {
        Transaction transaction = new Transaction();
        transaction.setWallet(wallet);
        transaction.setAmount(amount);
        transaction.setType(TransactionType.DEBIT);
        return transactionRepository.save(transaction);
    }

    @Transactional
    public Wallet addFunds(Long walletId, BigDecimal amount) {
        validateAmount(amount, appConfig.getSend().getMinAmount(), appConfig.getSend().getMaxAmount());

        Wallet wallet = walletService.findWalletById(walletId);
        wallet.credit(amount);

        Transaction transaction = createCreditTransaction(wallet, amount);
        wallet.addTransaction(transaction);

        return wallet;
    }

    @Transactional
    public Wallet withdrawFunds(Long walletId, BigDecimal amount) {
        validateAmount(amount, appConfig.getWithdraw().getMinAmount(), appConfig.getWithdraw().getMaxAmount());

        Wallet wallet = walletService.findWalletById(walletId);

        if (wallet.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException("Insufficient funds in wallet");
        }

        wallet.debit(amount);

        Transaction transaction = createDebitTransaction(wallet, amount);
        wallet.addTransaction(transaction);

        return wallet;
    }

    public Page<Transaction> getTransactions(Long walletId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return transactionRepository.findByWalletId(walletId, pageRequest);
    }

    private void validateAmount(BigDecimal amount, BigDecimal minAmount, BigDecimal maxAmount) {
        if (amount.compareTo(minAmount) < 0 || amount.compareTo(maxAmount) > 0) {
            throw new InvalidAmountException("Invalid transaction amount");
        }
    }
}
