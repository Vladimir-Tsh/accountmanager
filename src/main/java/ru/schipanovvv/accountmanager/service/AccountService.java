package ru.schipanovvv.accountmanager.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.schipanovvv.accountmanager.dto.AccountState;
import ru.schipanovvv.accountmanager.dto.AccountDTO;
import ru.schipanovvv.accountmanager.db.Account;
import ru.schipanovvv.accountmanager.repositiry.AccountCrudRepository;

import java.math.BigDecimal;

@Service
public class AccountService {
    private AccountCrudRepository accountCrudRepository;

    public AccountService(AccountCrudRepository accountCrudRepository) {
        this.accountCrudRepository = accountCrudRepository;
    }

    public AccountDTO getAccount(int id) {
        Account account = accountCrudRepository.findById(id).orElseThrow(RuntimeException::new);
        return new AccountDTO(account.getId(), AccountState.valueOf(account.getState()), BigDecimal.valueOf(account.getBalance()));
    }

    @Transactional
    public AccountDTO changeAccountState(int id) {
        Account account = accountCrudRepository.findById(id).orElseThrow(RuntimeException::new);
        if (account.getState().equals(AccountState.OPEN.toString())) {
            account.setState(AccountState.BLOCKED.toString());
        } else {
            account.setState(AccountState.OPEN.toString());
        }
        return new AccountDTO(account.getId(), AccountState.valueOf(account.getState()), BigDecimal.valueOf(account.getBalance()));
    }

    @Transactional
    public AccountDTO setAccountBalance(int id, double balance) {
        Account account = accountCrudRepository.findById(id).orElseThrow(RuntimeException::new);
        account.setBalance(balance);
        return new AccountDTO(account.getId(), AccountState.valueOf(account.getState()), BigDecimal.valueOf(account.getBalance()));
    }
}
