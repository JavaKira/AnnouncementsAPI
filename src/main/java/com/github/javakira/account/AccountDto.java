package com.github.javakira.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    private long id;
    private String username;

    public static AccountDto from(Account account) {
        return AccountDto
                .builder()
                .id(account.getId())
                .username(account.getUsername())
                .build();
    }
}
