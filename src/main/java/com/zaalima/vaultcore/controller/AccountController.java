package com.zaalima.vaultcore.controller;

import com.zaalima.vaultcore.dto.AccountResponse;
import com.zaalima.vaultcore.entity.Account;
import com.zaalima.vaultcore.entity.User;
import com.zaalima.vaultcore.enums.TransactionType;
import com.zaalima.vaultcore.repository.UserRepository;
import com.zaalima.vaultcore.service.AccountService;
import com.zaalima.vaultcore.service.TransactionService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;
    private final UserRepository userRepository;
    private final TransactionService transactionService;

    public AccountController(AccountService accountService,
                             UserRepository userRepository,
                             TransactionService transactionService) {
        this.accountService = accountService;
        this.userRepository = userRepository;
        this.transactionService = transactionService;
    }

    // ✅ CREATE ACCOUNT (ONE PER USER)
    @PostMapping
    public ResponseEntity<AccountResponse> createAccount() {

        User user = getLoggedInUser();
        Account account = accountService.createAccount(user);

        return ResponseEntity.ok(
                new AccountResponse(
                        account.getAccountNumber(),
                        account.getBalance()
                )
        );
    }

    // ✅ GET LOGGED-IN USER ACCOUNT
    @GetMapping("/me")
    public ResponseEntity<AccountResponse> getMyAccount() {

        User user = getLoggedInUser();
        Account account = accountService.getAccountForUser(user);

        return ResponseEntity.ok(
                new AccountResponse(
                        account.getAccountNumber(),
                        account.getBalance()
                )
        );
    }

    // ✅ DEPOSIT MONEY
    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestParam BigDecimal amount) {

        User user = getLoggedInUser();
        Account account = accountService.getAccountForUser(user);

        // 🔑 ONLY TransactionService updates balance
        transactionService.create(account, TransactionType.DEPOSIT, amount);

        return ResponseEntity.ok("Deposit successful");
    }

    // ✅ WITHDRAW MONEY
    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestParam BigDecimal amount) {

        User user = getLoggedInUser();
        Account account = accountService.getAccountForUser(user);

        // 🔑 ONLY TransactionService updates balance
        transactionService.create(account, TransactionType.WITHDRAW, amount);

        return ResponseEntity.ok("Withdrawal successful");
    }

    // 🔒 COMMON METHOD – GET LOGGED-IN USER
    private User getLoggedInUser() {

        Authentication auth =
                SecurityContextHolder.getContext().getAuthentication();

        return userRepository.findByUsername(auth.getName())
                .orElseThrow(() ->
                        new IllegalStateException("User not found"));
    }
}
