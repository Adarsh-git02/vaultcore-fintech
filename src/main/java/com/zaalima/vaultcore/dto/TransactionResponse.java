package com.zaalima.vaultcore.dto;

import com.zaalima.vaultcore.enums.TransactionType;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionResponse {

    private TransactionType type;
    private BigDecimal amount;
    private LocalDateTime timestamp;

    public TransactionResponse(TransactionType type,
                               BigDecimal amount,
                               LocalDateTime timestamp) {
        this.type = type;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public TransactionType getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
