package com.github.lukaslt1993.wallet.entity;

import com.github.lukaslt1993.wallet.dto.TransactionDTO;
import com.github.lukaslt1993.wallet.dto.TransactionPageDTO;
import com.github.lukaslt1993.wallet.enums.TransactionType;
import jakarta.persistence.*;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private BigDecimal amount;
    private LocalDateTime timestamp;
    private TransactionType type;

    @ManyToOne
    private Wallet wallet;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public static TransactionDTO toTransactionDTO(Transaction transaction) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setId(transaction.getId());
        transactionDTO.setAmount(transaction.getAmount());
        transactionDTO.setTimestamp(transaction.getTimestamp());
        transactionDTO.setType(transaction.getType());
        return transactionDTO;
    }

    public static TransactionPageDTO toTransactionPageDTO(Page<Transaction> transactionPage) {
        TransactionPageDTO transactionPageDto = new TransactionPageDTO();
        transactionPageDto.setTransactions(
                transactionPage.getContent().stream()
                .map(Transaction::toTransactionDTO)
                .collect(Collectors.toList())
        );
        transactionPageDto.setCurrentPage(transactionPage.getNumber());
        transactionPageDto.setTotalPages(transactionPage.getTotalPages());
        transactionPageDto.setTotalElements(transactionPage.getTotalElements());
        return transactionPageDto;
    }
}
