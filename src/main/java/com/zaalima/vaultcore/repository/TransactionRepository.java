package com.zaalima.vaultcore.repository;

import com.zaalima.vaultcore.entity.Account;
import com.zaalima.vaultcore.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByAccountOrderByTimestampDesc(Account account);
}
