package com.github.javakira.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository repository;

    @Override
    public void update(UpdateRoleRequest updateRoleRequest, long userId, long id) {

    }

    @Override
    public AccountDto update(UpdateAccountRequest updateAccountRequest, long userId, long id) {
        return null;
    }

    @Override
    public void delete(long userId, long id) {

    }

    @Override
    public void restrictAdPublication(long userId, long id) {

    }
}
