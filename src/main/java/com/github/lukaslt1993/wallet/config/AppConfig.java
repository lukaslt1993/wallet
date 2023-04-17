package com.github.lukaslt1993.wallet.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Currency;

@Configuration
@ConfigurationProperties(prefix = "app")
@Getter
@Setter
public class AppConfig {

    private Withdraw withdraw;
    private Send send;

    @Getter
    @Setter
    public static class Withdraw {
        private BigDecimal minAmount;
        private BigDecimal maxAmount;
    }

    @Getter
    @Setter
    public static class Send {
        private BigDecimal minAmount;
        private BigDecimal maxAmount;
    }

    private Currency defaultCurrency;
}
