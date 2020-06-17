package ru.schipanovvv.accountmanager.dto;

public class AccountDTO {
    private final int accountId;
    private final AccountState accountState;
    private final float accountBalance;

    public AccountDTO(int accountId, AccountState accountState, float accountBalance) {
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

    public float getAccountBalance() {
        return accountBalance;
    }
}
