package com.github.javakira.auth;

import com.github.javakira.jwt.JwtService;
import jakarta.servlet.http.HttpServletRequest;
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
    private final JwtService jwtService;

    @PostMapping
    AuthResponse auth(@RequestBody AuthRequest request) {
        return service.auth(request);
    }

    @PostMapping("/register")
    AuthResponse register(@RequestBody RegisterRequest request) {
        return service.register(request);
    }

    @PostMapping("/logout")
    void logout(HttpServletRequest request) {
        String jwt = jwtService.token(request);
        service.logout(jwt);
    }
}
