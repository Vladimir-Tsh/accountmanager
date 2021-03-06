package ru.schipanovvv.accountmanager.dto;

import java.math.BigDecimal;
import java.util.Date;

public class OperationDTO {
    private int accountId;
    private Date operationDate;
    private OperationType operationType;
    private BigDecimal sum;
    private int pid;
    private OperationState operationState;

    public OperationDTO() {
    }

    public OperationDTO(int accountId, Date operationDate, OperationType operationType, BigDecimal sum, int pid, OperationState operationState) {
        this.accountId = accountId;
        this.operationDate = operationDate;
        this.operationType = operationType;
        this.sum = sum;
        this.pid = pid;
        this.operationState = operationState;
    }

    public int getAccountId() {
        return accountId;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public int getPid() {
        return pid;
    }

    public OperationState getOperationState() {
        return operationState;
    }
}
