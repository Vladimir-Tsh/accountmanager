package ru.schipanovvv.accountmanager.service;

import org.springframework.stereotype.Service;
import ru.schipanovvv.accountmanager.dto.OperationDTO;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class QueueService {
    private final Queue<String> allOperationsQueue = new ConcurrentLinkedQueue<>();
    private final Queue<OperationDTO> waitingOperationsQueue = new ConcurrentLinkedQueue<>();

    public Queue getAllOperationsQueue() {
        return allOperationsQueue;
    }

    public Queue getWaitingOperationsQueue() {
        return waitingOperationsQueue;
    }
}
