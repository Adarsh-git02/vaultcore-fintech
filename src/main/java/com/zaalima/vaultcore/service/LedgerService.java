package com.zaalima.vaultcore.service;

import com.zaalima.vaultcore.entity.Account;
import com.zaalima.vaultcore.entity.LedgerEntry;
import com.zaalima.vaultcore.repository.LedgerRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class LedgerService {

    private final LedgerRepository ledgerRepository;

    public LedgerService(LedgerRepository ledgerRepository) {
        this.ledgerRepository = ledgerRepository;
    }

    public void record(Account account,
                       BigDecimal amount,
                       boolean isCredit) {

        LedgerEntry entry = new LedgerEntry();
        entry.setAccount(account);
        entry.setAmount(amount);
        entry.setEntryType(isCredit ? "CREDIT" : "DEBIT");
        entry.setReference("TXN-" + UUID.randomUUID());
        entry.setTimestamp(LocalDateTime.now());

        ledgerRepository.save(entry);
    }
}
