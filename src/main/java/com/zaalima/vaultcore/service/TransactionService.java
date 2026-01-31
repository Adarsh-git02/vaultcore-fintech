package com.zaalima.vaultcore.service;

import com.zaalima.vaultcore.entity.Account;
import com.zaalima.vaultcore.entity.Transaction;
import com.zaalima.vaultcore.enums.TransactionType;
import com.zaalima.vaultcore.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    // ✅ GET ALL TRANSACTIONS FOR ACCOUNT
    public List<Transaction> getTransactionsForAccount(Account account) {
        return transactionRepository
                .findByAccountOrderByTimestampDesc(account);
    }

    // ✅ CREATE TRANSACTION ENTRY
    @Transactional
    public Transaction create(Account account,
                              TransactionType type,
                              BigDecimal amount) {

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setType(type);
        transaction.setAmount(amount);
        transaction.setTimestamp(LocalDateTime.now());

        return transactionRepository.save(transaction);
    }
}
