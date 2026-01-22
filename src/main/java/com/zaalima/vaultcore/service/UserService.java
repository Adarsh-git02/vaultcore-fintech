package com.zaalima.vaultcore.service;

import com.zaalima.vaultcore.entity.User;
import com.zaalima.vaultcore.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AccountService accountService;

    public UserService(UserRepository userRepository,
                       AccountService accountService) {
        this.userRepository = userRepository;
        this.accountService = accountService;
    }

    public User registerUser(User user) {
        User savedUser = userRepository.save(user);
        accountService.createAccountForUser(savedUser);
        return savedUser;
    }
}
