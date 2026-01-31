package com.zaalima.vaultcore.controller;

import com.zaalima.vaultcore.dto.TransactionResponse;
import com.zaalima.vaultcore.entity.Account;
import com.zaalima.vaultcore.entity.User;
import com.zaalima.vaultcore.repository.UserRepository;
import com.zaalima.vaultcore.service.AccountService;
import com.zaalima.vaultcore.service.TransactionService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final AccountService accountService;
    private final UserRepository userRepository;

    public TransactionController(TransactionService transactionService,
                                 AccountService accountService,
                                 UserRepository userRepository) {
        this.transactionService = transactionService;
        this.accountService = accountService;
        this.userRepository = userRepository;
    }

    @GetMapping("/me")
    public ResponseEntity<List<TransactionResponse>> getMyTransactions() {

        Authentication auth =
                SecurityContextHolder.getContext().getAuthentication();

        User user = userRepository.findByUsername(auth.getName())
                .orElseThrow(() ->
                        new IllegalStateException("User not found"));

        Account account = accountService.getAccountForUser(user);

        List<TransactionResponse> response =
                transactionService.getTransactionsForAccount(account)
                        .stream()
                        .map(tx -> new TransactionResponse(
                                tx.getType(),
                                tx.getAmount(),
                                tx.getTimestamp()
                        ))
                        .toList();

        return ResponseEntity.ok(response);
    }
}
