package com.github.lukaslt1993.wallet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionPageDTO {
    private List<TransactionDTO> transactions;
    private int currentPage;
    private int totalPages;
    private long totalElements;
}