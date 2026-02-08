package com.zaalima.vaultcore.service;

import com.zaalima.vaultcore.entity.Account;
import com.zaalima.vaultcore.entity.Transaction;
import com.zaalima.vaultcore.enums.TransactionType;
import com.zaalima.vaultcore.repository.AccountRepository;
import com.zaalima.vaultcore.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final LedgerService ledgerService;
    private final FraudDetectionService fraudDetectionService;

    public TransactionService(TransactionRepository transactionRepository,
                              AccountRepository accountRepository,
                              LedgerService ledgerService,
                              FraudDetectionService fraudDetectionService) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.ledgerService = ledgerService;
        this.fraudDetectionService = fraudDetectionService;
    }

    public List<Transaction> getTransactionsForAccount(Account account) {
        return transactionRepository.findByAccountOrderByTimestampDesc(account);
    }

    public Transaction create(Account account,
                              TransactionType type,
                              BigDecimal amount) {

        // 🛑 FRAUD CHECK (STEP 2)
        fraudDetectionService.validateWithdrawal(account, amount);

        if (type == TransactionType.WITHDRAW &&
                account.getBalance().compareTo(amount) < 0) {
            throw new IllegalStateException("Insufficient balance");
        }

        boolean isCredit = type == TransactionType.DEPOSIT;

        if (isCredit) {
            account.setBalance(account.getBalance().add(amount));
        } else {
            account.setBalance(account.getBalance().subtract(amount));
        }

        accountRepository.save(account);

        // 📘 LEDGER ENTRY
        ledgerService.record(account, amount, isCredit);

        Transaction tx = new Transaction();
        tx.setAccount(account);
        tx.setType(type);
        tx.setAmount(amount);
        tx.setTimestamp(LocalDateTime.now());

        return transactionRepository.save(tx);
    }
}
