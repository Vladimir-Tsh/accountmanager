package ru.schipanovvv.accountmanager.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.schipanovvv.accountmanager.dto.AccountDTO;
import ru.schipanovvv.accountmanager.service.AccountService;

@RestController
public class AccountController {
    private AccountService accountService;

    public AccountController(AccountService acountService) {
        this.accountService = acountService;
    }

    @GetMapping("/account")
    public AccountDTO getAccount(@RequestParam(value = "id", defaultValue = "1") int id) {
        return accountService.getAccount(id);
    }

    @GetMapping("/account/{id}/changestate")
    public AccountDTO changeAccountState(@PathVariable("id") int id) {
        return accountService.changeAccountState(id);
    }
}
