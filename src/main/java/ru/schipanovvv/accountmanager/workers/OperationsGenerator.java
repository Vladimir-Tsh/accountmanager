package ru.schipanovvv.accountmanager.workers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.schipanovvv.accountmanager.dto.OperationDTO;
import ru.schipanovvv.accountmanager.dto.OperationState;
import ru.schipanovvv.accountmanager.dto.OperationType;
import ru.schipanovvv.accountmanager.service.QueueService;

import java.util.Date;

@Component
@Scope("prototype")
public class OperationsGenerator implements Runnable {
    private static int countOperatorGenerator;
    private int pid;
    private QueueService queueService;

    public OperationsGenerator(QueueService queueService) {
        this.queueService = queueService;
        countOperatorGenerator++;
        this.pid = countOperatorGenerator;
    }

    @Override
    public void run() {
        boolean needWork = true;
        ObjectMapper mapper = new ObjectMapper();
        OperationDTO operationDTO;
        String operationInJSON;
        System.out.println("OperationsGenerator " + getPid() + " start.");
        while (needWork) {
            operationDTO = ganerateOperation();
            try {
                operationInJSON = mapper.writeValueAsString(operationDTO);
                queueService.getAllOperationsQueue().add(operationInJSON);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            needWork = false;
        }
    }

    public int getPid() {
        return pid;
    }

    public OperationDTO ganerateOperation() {
        OperationType operationType;
        float sum;
        if ((int) (Math.random() * 2) == 0)
            operationType = OperationType.CREDIT;
        else
            operationType = OperationType.DEBET;
        sum = ((int) (Math.random() * 1000)) / 100f;
        return new OperationDTO(1,new Date(), operationType, sum, getPid(), OperationState.CREATED);
    }
}
