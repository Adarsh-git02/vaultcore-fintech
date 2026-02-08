package com.zaalima.vaultcore.repository;

import com.zaalima.vaultcore.entity.Account;
import com.zaalima.vaultcore.entity.LedgerEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LedgerEntryRepository
        extends JpaRepository<LedgerEntry, Long> {

    List<LedgerEntry> findByAccountOrderByTimestampDesc(Account account);
}
