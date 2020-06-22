package ru.schipanovvv.accountmanager.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.schipanovvv.accountmanager.db.AccountOperation;
import ru.schipanovvv.accountmanager.dto.*;
import ru.schipanovvv.accountmanager.repositiry.AccountOperationCrudRepository;

import java.math.BigDecimal;

@Service
public class AccountOperationService {
    private AccountOperationCrudRepository accountOperationCrudRepository;
    private AccountService accountService;

    public AccountOperationService(AccountOperationCrudRepository accountOperationCrudRepository,
                                   AccountService accountService) {
        this.accountOperationCrudRepository = accountOperationCrudRepository;
        this.accountService = accountService;
    }

    @Transactional
    public AccountOperationDTO newAccountOperation(OperationDTO operationDTO) {

        AccountOperation accountOperation = new AccountOperation(operationDTO.getAccountId(),
                operationDTO.getOperationDate(),
                operationDTO.getOperationType().toString(),
                operationDTO.getSum().doubleValue(),
                operationDTO.getPid(),
                operationDTO.getOperationState().toString());

        AccountDTO accountDTO = accountService.getAccount(operationDTO.getAccountId());

        if (accountDTO.getAccountState().equals(AccountState.BLOCKED) ||
                accountDTO.getAccountState().equals(AccountState.CLOSED)) {

            accountOperation.setOperationState(OperationState.DENIED.toString());

        } else if (operationDTO.getOperationType().equals(OperationType.DEBET)) {

            BigDecimal newAccountBalance = accountDTO.getAccountBalance().add(operationDTO.getSum());

            accountDTO = accountService.setAccountBalance(operationDTO.getAccountId(), newAccountBalance.doubleValue());
            accountOperation.setOperationState(OperationState.EXECUTED.toString());

        } else if (accountDTO.getAccountBalance().compareTo(operationDTO.getSum()) > 0) {

            BigDecimal newAccountBalance = accountDTO.getAccountBalance().subtract(operationDTO.getSum());

            accountDTO = accountService.setAccountBalance(operationDTO.getAccountId(), newAccountBalance.doubleValue());
            accountOperation.setOperationState(OperationState.EXECUTED.toString());

        } else {

            accountOperation.setOperationState(OperationState.WAITING.toString());

        }

        accountOperation = accountOperationCrudRepository.save(accountOperation);

        System.out.println(accountOperation.getId() + " : " + accountOperation.getAccountId() + " : " + accountOperation.getSum() + " : " + accountOperation.getPid() + " : " + accountOperation.getOperationState());

        return new AccountOperationDTO(accountOperation.getId(), accountOperation.getAccountId(),
                accountOperation.getOperationDate(),
                OperationType.valueOf(accountOperation.getOperationType()),
                BigDecimal.valueOf(accountOperation.getSum()),
                accountOperation.getPid(),
                OperationState.valueOf(accountOperation.getOperationState()));
    }

    @Transactional
    public AccountOperationDTO executeWaitOperation(AccountOperationDTO accountOperationDTO) {
        AccountDTO accountDTO = accountService.getAccount(accountOperationDTO.getAccountId());
        AccountOperation accountOperation = accountOperationCrudRepository.findById(accountOperationDTO.getOperationId()).orElseThrow(RuntimeException::new);

        if (accountDTO.getAccountState().equals(AccountState.BLOCKED) ||
                accountDTO.getAccountState().equals(AccountState.CLOSED)) {

            accountOperation.setOperationState(OperationState.DENIED.toString());

        } else if (accountDTO.getAccountBalance().compareTo(accountOperationDTO.getSum()) > 0) {

            BigDecimal newAccountBalance = accountDTO.getAccountBalance().subtract(accountOperationDTO.getSum());

            accountDTO = accountService.setAccountBalance(accountOperationDTO.getAccountId(), newAccountBalance.doubleValue());
            accountOperation.setOperationState(OperationState.EXECUTED.toString());

        }

        System.out.println(accountOperation.getId() + " : " + accountOperation.getAccountId() + " : " + accountOperation.getSum() + " : " + accountOperation.getPid() + " : " + accountOperation.getOperationState());

        return new AccountOperationDTO(accountOperation.getId(), accountOperation.getAccountId(),
                accountOperation.getOperationDate(),
                OperationType.valueOf(accountOperation.getOperationType()),
                BigDecimal.valueOf(accountOperation.getSum()),
                accountOperation.getPid(),
                OperationState.valueOf(accountOperation.getOperationState()));
    }
}
