package com.zaalima.vaultcore.service;

import com.zaalima.vaultcore.entity.Transaction;
import com.zaalima.vaultcore.entity.Account;
import com.zaalima.vaultcore.entity.User;
import com.zaalima.vaultcore.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    // CREATE
    public Account createAccount(User user) {

        Optional<Account> existingAccount =
                accountRepository.findByUser(user);

        if (existingAccount.isPresent()) {
            throw new IllegalStateException("User already has an account");
        }

        Account account = new Account();
        account.setUser(user);
        account.setBalance(BigDecimal.ZERO);
        account.setAccountNumber(generateAccountNumber());

        return accountRepository.save(account);
    }

    // READ (NEW)
    public Account getAccountForUser(User user) {
        return accountRepository.findByUser(user)
                .orElseThrow(() ->
                        new IllegalStateException("Account not found"));
    }

    private String generateAccountNumber() {
        return "ACC-" + UUID.randomUUID()
                .toString()
                .substring(0, 10)
                .toUpperCase();
    }

    public Account deposit(Account account, BigDecimal amount) {
    account.setBalance(account.getBalance().add(amount));
    return accountRepository.save(account);
}

public Account withdraw(Account account, BigDecimal amount) {
    if (account.getBalance().compareTo(amount) < 0) {
        throw new IllegalStateException("Insufficient balance");
    }
    account.setBalance(account.getBalance().subtract(amount));
    return accountRepository.save(account);
}

}
