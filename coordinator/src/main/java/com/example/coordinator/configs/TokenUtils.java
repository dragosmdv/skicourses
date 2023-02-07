package com.example.coordinator.configs;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
@Slf4j
@RequiredArgsConstructor
public class TokenUtils {

    private final String tokenEncryptionSecret = "secret";

    public String extractUsername(HttpServletRequest request) {
        Algorithm algorithm = Algorithm.HMAC256(tokenEncryptionSecret.getBytes());
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        String username = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring("Bearer ".length());
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            DecodedJWT decodedJWT = verifier.verify(token);
            username = decodedJWT.getSubject();
            log.info("Successfully extracted username from token: {}", username);
        }
        return username;
    }

    public Collection<SimpleGrantedAuthority> extractAuthorities(HttpServletRequest request) {
        Algorithm algorithm = Algorithm.HMAC256(tokenEncryptionSecret.getBytes());
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        Collection<SimpleGrantedAuthority> authorities = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring("Bearer ".length());
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            DecodedJWT decodedJWT = verifier.verify(token);
            String[] roles = decodedJWT.getClaim("role")
                    .asArray(String.class);
            Collection<SimpleGrantedAuthority> finalAuthorities = new ArrayList<>();
            stream(roles).forEach(role -> {
                finalAuthorities.add(new SimpleGrantedAuthority(role));
            });
            authorities = finalAuthorities;
            log.info("Successfully extracted authorities from token: {}", authorities.stream()
                    .findFirst());
        }
        return authorities;
    }

    public String createAccessToken(HttpServletRequest request,
                                    String username,
                                    List<String> authorities) {
        Algorithm algorithm = Algorithm.HMAC256(tokenEncryptionSecret.getBytes());
        int tokenDurationAccess = 100;
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + (long) tokenDurationAccess * 60 * 1000))
                .withIssuer(request.getRequestURL()
                        .toString())
                .withClaim("role", authorities)
                .sign(algorithm);
    }

    public String createRefreshToken(HttpServletRequest request,
                                     String username
    ) {
        Algorithm algorithm = Algorithm.HMAC256(tokenEncryptionSecret.getBytes());
        int tokenDurationRefresh = 600;
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + (long) tokenDurationRefresh * 60 * 1000))
                .withIssuer(request.getRequestURL()
                        .toString())
                .sign(algorithm);
    }

    public void returnTokens(HttpServletResponse response,
                             String accessToken,
                             String refreshToken) throws IOException {
        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", accessToken);
        tokens.put("refresh_token", refreshToken);
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }
}
