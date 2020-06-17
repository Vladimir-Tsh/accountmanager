package ru.schipanovvv.accountmanager.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.schipanovvv.accountmanager.db.AccountOperation;
import ru.schipanovvv.accountmanager.dto.OperationDTO;
import ru.schipanovvv.accountmanager.repositiry.AccountOperationCrudRepository;

@Service
public class AccountOperationService {
    private AccountOperationCrudRepository accountOperationCrudRepository;

    public AccountOperationService(AccountOperationCrudRepository accountOperationCrudRepository) {
        this.accountOperationCrudRepository = accountOperationCrudRepository;
    }

    @Transactional
    public void newAccountOperation(OperationDTO operationDTO) {
        AccountOperation accountOperation = new AccountOperation(operationDTO.getAccountId(),
                operationDTO.getOperatiopnDate(),
                operationDTO.getOperationType().toString(),
                operationDTO.getSum(),
                operationDTO.getPid(),
                operationDTO.getOperationState().toString());
        accountOperation = accountOperationCrudRepository.save(accountOperation);
        System.out.println(accountOperation.getId() + " : " + accountOperation.getAccountId() + " : " + accountOperation.getSum() + " : " + accountOperation.getPid());
    }
}
