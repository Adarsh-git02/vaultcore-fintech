package com.zaalima.vaultcore.repository;

import com.zaalima.vaultcore.entity.Account;
import com.zaalima.vaultcore.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    // ✅ used to prevent duplicate accounts
    Optional<Account> findByUser(User user);

    // ✅ used for JWT-based lookup
    Optional<Account> findByUserUsername(String username);
}
