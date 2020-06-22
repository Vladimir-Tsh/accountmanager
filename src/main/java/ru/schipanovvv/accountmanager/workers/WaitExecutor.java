package ru.schipanovvv.accountmanager.workers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.schipanovvv.accountmanager.dto.AccountOperationDTO;
import ru.schipanovvv.accountmanager.dto.OperationState;
import ru.schipanovvv.accountmanager.service.AccountOperationService;
import ru.schipanovvv.accountmanager.service.QueueService;

@Component
@Scope("prototype")
public class WaitExecutor implements Runnable {
    private QueueService queueService;
    private AccountOperationService accountOperationService;

    public WaitExecutor(QueueService queueService, AccountOperationService accountOperationService) {
        this.queueService = queueService;
        this.accountOperationService = accountOperationService;
    }

    @Override
    public void run() {
        boolean needWork = true;
        AccountOperationDTO accountOperationDTO = null;
        System.out.println("WaitExecutor start.");
        while (needWork) {
            if (!queueService.getWaitingOperationsQueue().isEmpty()) {
                accountOperationDTO = (AccountOperationDTO) queueService.getWaitingOperationsQueue().poll();
                accountOperationDTO = accountOperationService.executeWaitOperation(accountOperationDTO);
                if (accountOperationDTO.getOperationState().equals(OperationState.WAITING)) {
                    queueService.getWaitingOperationsQueue().add(accountOperationDTO);
                }
            }
            /*try {
                Thread.sleep(1000/(1 + queueService.getWaitingOperationsQueue().size()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
    }
}
