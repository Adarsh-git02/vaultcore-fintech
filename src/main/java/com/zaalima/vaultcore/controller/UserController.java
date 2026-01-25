package com.zaalima.vaultcore.controller;

import com.zaalima.vaultcore.dto.UserRegistrationRequest;
import com.zaalima.vaultcore.entity.User;
import com.zaalima.vaultcore.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(
            @RequestBody UserRegistrationRequest request) {

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());

        userService.registerUser(user);

        return ResponseEntity.ok("User registered successfully");
    }

    // ✅ NEW ENDPOINT
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        return ResponseEntity.ok(authentication.getName());
    }
}
