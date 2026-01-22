package com.zaalima.vaultcore.service;

import com.zaalima.vaultcore.entity.User;
import com.zaalima.vaultcore.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       AccountService accountService,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.accountService = accountService;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        accountService.createAccountForUser(savedUser);
        return savedUser;
    }
}
