package com.github.javakira.account;

import com.github.javakira.jwt.JwtService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
class AccountController {
    private final AccountService service;
    private final JwtService jwtService;

    @PutMapping("/role/{id}")
    void updateRole(HttpServletRequest request, @RequestBody UpdateRoleRequest updateRoleRequest, @PathVariable long id) {
        long userId = jwtService.extractId(request);
        service.update(updateRoleRequest, userId, id);
    }

    @PatchMapping("/{id}")
    AccountDto updateAccount(HttpServletRequest request, @RequestBody UpdateAccountRequest updateAccountRequest, @PathVariable long id) {
        long userId = jwtService.extractId(request);
        return service.update(updateAccountRequest, userId, id);
    }

    @DeleteMapping("/{id}")
    void deleteAccount(HttpServletRequest request, @PathVariable long id) {
        long userId = jwtService.extractId(request);
        service.delete(userId, id);
    }

    @PostMapping("/{id}")
    void restrictAdPublication(HttpServletRequest request, @PathVariable long id) { //todo думаю можно сделать так: если возможность уже ограничена, то освобождать пользователя
        long userId = jwtService.extractId(request);
        service.restrictAdPublication(userId, id);
    }
}
