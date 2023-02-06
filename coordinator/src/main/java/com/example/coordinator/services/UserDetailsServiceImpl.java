package com.example.coordinator.services;

import com.example.coordinator.models.Account;
import com.example.coordinator.models.CustomUser;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Log4j2
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountService accountService;


    @Override
    public CustomUser loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("Fetching account with email = " + email);
        Account userInfo = accountService.getByEmail(email);
        return new CustomUser(userInfo.getEmail(), userInfo.getPassword(),
                userInfo.getId(), userInfo.getRole());
    }
}

