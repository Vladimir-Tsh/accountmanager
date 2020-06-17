package ru.schipanovvv.accountmanager.dto;

import java.util.Date;

public class OperationDTO {
    private int accountId;
    private Date operatiopnDate;
    private OperationType operationType;
    private float sum;
    private int pid;
    private OperationState operationState;

    public OperationDTO() {
    }

    public OperationDTO(int accountId, Date operatopnDate, OperationType operationType, float sum, int pid, OperationState operationState) {
        this.accountId = accountId;
        this.operatiopnDate = operatopnDate;
        this.operationType = operationType;
        this.sum = sum;
        this.pid = pid;
        this.operationState = operationState;
    }

    public int getAccountId() {
        return accountId;
    }

    public Date getOperatiopnDate() {
        return operatiopnDate;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public float getSum() {
        return sum;
    }

    public int getPid() {
        return pid;
    }

    public OperationState getOperationState() {
        return operationState;
    }
}
