package com.github.lukaslt1993.wallet.entity;

import com.github.lukaslt1993.wallet.dto.CustomerDTO;
import com.github.lukaslt1993.wallet.dto.WalletDTO;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Wallet> wallets = new ArrayList<>();

    private String name;

    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addWallet(Wallet wallet) {
        wallets.add(wallet);
        wallet.setCustomer(this);
    }

    public void removeWallet(Wallet wallet) {
        wallets.remove(wallet);
        wallet.setCustomer(null);
    }

    public Long getId() {
        return id;
    }

    public List<Wallet> getWallets() {
        return wallets;
    }

    public static CustomerDTO toCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());
        List<WalletDTO> walletDTOs = customer.getWallets().stream().map(Wallet::toWalletDTO).collect(Collectors.toList());
        customerDTO.setWallets(walletDTOs);
        return customerDTO;
    }
}