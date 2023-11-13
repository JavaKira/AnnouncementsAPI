package com.github.javakira.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
class AuthController {
    private final AuthService service;

    @PostMapping
    AuthResponse auth(@RequestBody AuthRequest request) {
        return service.auth(request);
    }

    @PostMapping("/register")
    AuthResponse register(@RequestBody RegisterRequest request) {
        return service.register(request);
    }

    @PostMapping("/logout")
    void logout(@RequestBody LogoutRequest request) {
        service.logout(request);
    }
}
