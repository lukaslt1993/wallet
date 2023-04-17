package com.github.lukaslt1993.wallet.dto;

import com.github.lukaslt1993.wallet.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
    private Long id;
    private BigDecimal amount;
    private LocalDateTime timestamp;
    private TransactionType type;
    private Long walletId;
    private Long customerId;
}
