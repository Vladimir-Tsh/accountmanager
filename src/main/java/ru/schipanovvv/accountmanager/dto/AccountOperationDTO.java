package ru.schipanovvv.accountmanager.dto;

import java.math.BigDecimal;
import java.util.Date;

public class AccountOperationDTO extends OperationDTO {
    private int operationId;

    public AccountOperationDTO(int operationId, int accountId, Date operationDate, OperationType operationType, BigDecimal sum, int pid, OperationState operationState) {
        super(accountId, operationDate, operationType, sum, pid, operationState);
        this.operationId = operationId;
    }

    public int getOperationId() {
        return operationId;
    }
}
