package ru.schipanovvv.accountmanager.dto;

import java.math.BigDecimal;

public class AccountDTO {
    private final int accountId;
    private final AccountState accountState;
    private final BigDecimal accountBalance;

    public AccountDTO(int accountId, AccountState accountState, BigDecimal accountBalance) {
        this.accountId = accountId;
        this.accountState = accountState;
        this.accountBalance = accountBalance;
    }

    public int getAccountId() {
        return accountId;
    }

    public AccountState getAccountState() {
        return accountState;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }
}
