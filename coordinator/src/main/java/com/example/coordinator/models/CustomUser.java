package com.example.coordinator.models;

import java.util.Arrays;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Getter
@Component
public class CustomUser extends User {

    private final Long userId;

    public CustomUser(String username,
                      String password,
                      Long userId,
                      String role) {
        super(username, password, Arrays.asList(new SimpleGrantedAuthority(role)));
        this.userId = userId;
    }

}
