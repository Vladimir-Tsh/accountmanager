package ru.schipanovvv.accountmanager.rest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.schipanovvv.accountmanager.db.Account;
import ru.schipanovvv.accountmanager.dto.AccountDTO;
import ru.schipanovvv.accountmanager.dto.AccountState;
import ru.schipanovvv.accountmanager.repositiry.AccountCrudRepository;
import ru.schipanovvv.accountmanager.service.AccountService;

@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {

    @Mock
    private AccountCrudRepository accountCrudRepository;

    private AccountService accountService;

    @BeforeEach
    void setUp() {
        accountService = new AccountService(accountCrudRepository);
    }

    @Test
    void getAccountTest() {
        Account account = new Account("40412810300000000001", "OPEN", 0.1);
        account.setId(12);
        Mockito.when(accountCrudRepository.findById(Mockito.anyInt())).thenReturn(java.util.Optional.of(account));
        AccountDTO accountDTO = accountService.getAccount(1);
        Assertions.assertEquals(AccountState.OPEN, accountDTO.getAccountState());
    }
}
