package com.github.javakira.auth;

import com.github.javakira.account.Account;
import com.github.javakira.account.AccountRepository;
import com.github.javakira.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class AuthServiceImpl implements AuthService {
    private final AccountRepository repository; // todo можно заменить на сервис
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;

    @Override
    public AuthResponse auth(AuthRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        Account account = repository.findByUsername(request.getUsername())
                .orElseThrow();

        return new AuthResponse(jwtService.generateToken(account));
    }

    @Override
    public AuthResponse register(RegisterRequest request) {
        //todo checkUsername(request.getUsername());

        Account account = Account.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        repository.save(account);

        return new AuthResponse(jwtService.generateToken(account));
    }

    @Override
    public void logout(String jwt) {

    }
}
