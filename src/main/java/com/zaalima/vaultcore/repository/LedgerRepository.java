package com.zaalima.vaultcore.repository;

import com.zaalima.vaultcore.entity.LedgerEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LedgerRepository extends JpaRepository<LedgerEntry, Long> {
}
