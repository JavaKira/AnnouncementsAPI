package com.github.javakira.jwt;

import com.github.javakira.account.Account;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String extractUsername(String token);

    String generateToken(Account account);

    boolean isTokenValid(String token, UserDetails userDetails);
}
