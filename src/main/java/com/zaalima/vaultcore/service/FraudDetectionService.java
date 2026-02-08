package com.zaalima.vaultcore.service;

import com.zaalima.vaultcore.entity.Account;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class FraudDetectionService {

    // 🚨 RULE 1: Max withdrawal limit
    private static final BigDecimal MAX_WITHDRAWAL_LIMIT =
            new BigDecimal("50000");

    // 🚨 RULE 2: Low balance suspicious threshold
    private static final BigDecimal MIN_SAFE_BALANCE =
            new BigDecimal("100");

    /**
     * Perform fraud checks before transaction
     */
    public void validateWithdrawal(Account account, BigDecimal amount) {

        // Rule 1: Excessive withdrawal
        if (amount.compareTo(MAX_WITHDRAWAL_LIMIT) > 0) {
            throw new IllegalStateException(
                    "Fraud Alert: Withdrawal exceeds allowed limit"
            );
        }

        // Rule 2: Suspicious balance drain
        BigDecimal remaining =
                account.getBalance().subtract(amount);

        if (remaining.compareTo(MIN_SAFE_BALANCE) < 0) {
            throw new IllegalStateException(
                    "Fraud Alert: Suspicious balance drain detected"
            );
        }
    }
}
