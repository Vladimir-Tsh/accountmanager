package ru.schipanovvv.accountmanager.db;

import javax.persistence.*;
//import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "account_operations")
public class AccountOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "accountid")
    private int accountId;
    @Temporal(TemporalType.DATE)
    @Column(name = "opdate")
    private Date operationDate;
    @Column(name = "optype")
    private String operationType;
    @Column(name = "opsum")
    private float sum;
    @Column(name = "pid")
    private int pid;
    @Column(name = "opstate")
    private String operationState;

    public AccountOperation() {
    }

    public AccountOperation(int accountId, Date operationDate, String operationType, float sum, int pid, String operationState) {
        this.accountId = accountId;
        this.operationDate = operationDate;
        this.operationType = operationType;
        this.sum = sum;
        this.pid = pid;
        this.operationState = operationState;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operatiopnDate) {
        this.operationDate = operatiopnDate;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public float getSum() {
        return sum;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getOperationState() {
        return operationState;
    }

    public void setOperationState(String operationState) {
        this.operationState = operationState;
    }
}
