package com.zaalima.vaultcore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.zaalima.vaultcore")
public class VaultcoreApplication {

    static {
        System.out.println(">>> VAULTCORE APPLICATION STARTED <<<");
    }

    public static void main(String[] args) {
        SpringApplication.run(VaultcoreApplication.class, args);
    }
}
