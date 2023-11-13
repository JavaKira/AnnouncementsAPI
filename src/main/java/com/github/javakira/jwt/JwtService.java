package com.github.javakira.jwt;

import com.github.javakira.account.Account;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface JwtService {
    String extractUsername(String token);

    String generateToken(Account account);

    String token(HttpServletRequest request);

    long extractId(HttpServletRequest request);

    boolean isTokenValid(String token, UserDetails userDetails);
}
