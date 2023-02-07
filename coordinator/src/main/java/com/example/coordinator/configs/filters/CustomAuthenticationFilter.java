package com.example.coordinator.configs.filters;


import com.example.coordinator.configs.TokenUtils;
import com.example.coordinator.models.Account;
import com.example.coordinator.repositories.AccountRepository;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final TokenUtils tokenUtils;
    private final AccountRepository accountRepository;

    public CustomAuthenticationFilter(TokenUtils tokenUtils,
                                      AuthenticationManager authenticationManagerBean,
                                      AccountRepository accountRepository) {
        this.authenticationManager = authenticationManagerBean;
        this.tokenUtils = tokenUtils;
        this.accountRepository = accountRepository;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        log.info("Authentication attempted. Credentials: username={} password={}", username, password);
        Account user = accountRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("Authentication failed. Invalid username");
        }
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authentication) throws IOException {
        log.info("Authentication successful");
        // The principal is the user that's been authenticated
        User user = (User) authentication.getPrincipal();
        String username = user.getUsername();
        List<String> authorities =
                user.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList());
        String accessToken = tokenUtils.createAccessToken(request, username, authorities);
        String refreshToken = tokenUtils.createRefreshToken(request, username);
        tokenUtils.returnTokens(response, accessToken, refreshToken);
        log.info("Created and returned tokens in response body");
    }
}
