package com.example.coordinator.services;

import com.example.coordinator.models.Account;
import com.example.coordinator.repositories.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Log4j2
public class AccountService {

    private final AccountRepository accountRepository;

    public Account getByEmail(String email) {
        log.info("Fetching account with email = " + email);
        return accountRepository.findByEmail(email);
    }

}
