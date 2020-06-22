package ru.schipanovvv.accountmanager.workers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.schipanovvv.accountmanager.dto.AccountOperationDTO;
import ru.schipanovvv.accountmanager.dto.OperationDTO;
import ru.schipanovvv.accountmanager.dto.OperationState;
import ru.schipanovvv.accountmanager.service.AccountOperationService;
import ru.schipanovvv.accountmanager.service.QueueService;

@Component
@Scope("prototype")
public class MainExecutor implements Runnable {
    private QueueService queueService;
    private AccountOperationService accountOperationService;

    public MainExecutor(QueueService queueService, AccountOperationService accountOperationService) {
        this.queueService = queueService;
        this.accountOperationService = accountOperationService;
    }

    @Override
    public void run() {
        boolean needWork = true;
        ObjectMapper mapper = new ObjectMapper();
        OperationDTO operationDTO = null;
        AccountOperationDTO accountOperationDTO = null;
        String operationInJSON;
        System.out.println("MainExecutor start.");
        while (needWork) {
            if (!queueService.getAllOperationsQueue().isEmpty()) {
//                synchronized (this) {
                    operationInJSON = (String) queueService.getAllOperationsQueue().poll();

                    try {
                        operationDTO = mapper.readValue(operationInJSON, OperationDTO.class);
                        accountOperationDTO = accountOperationService.newAccountOperation(operationDTO);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }

                    if (accountOperationDTO.getOperationState().equals(OperationState.WAITING)) {
                        queueService.getWaitingOperationsQueue().add(accountOperationDTO);
                    }
//                }
            }
//            needWork = false;
        }
    }
}
