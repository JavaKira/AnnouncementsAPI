package com.github.javakira.account;

public interface AccountService {
    void update(UpdateRoleRequest updateRoleRequest, long userId, long id);

    AccountDto update(UpdateAccountRequest updateAccountRequest, long userId, long id);

    void delete(long userId, long id);

    void restrictAdPublication(long userId, long id);
}
