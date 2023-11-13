package com.github.javakira.auth;

interface AuthService {
    AuthResponse auth(AuthRequest request);

    AuthResponse register(RegisterRequest request);

    void logout(LogoutRequest request);
}
