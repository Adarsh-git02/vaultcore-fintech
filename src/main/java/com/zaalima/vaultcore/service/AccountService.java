package com.zaalima.vaultcore.service;

import com.zaalima.vaultcore.entity.Account;
import com.zaalima.vaultcore.entity.User;
import com.zaalima.vaultcore.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccountForUser(User user) {
        Account account = new Account();
        account.setUser(user);
        account.setBalance(BigDecimal.ZERO);
        return accountRepository.save(account);
    }
}
