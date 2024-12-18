package com.stress.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.stress.data.service.MessageUtils.generateContent;

@Service
public class KafkaProducerBufferedService {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    private final static int BUFFER_SIZE = 20000;

    private final List<String> messageBuffer = new ArrayList<>();
    private final Object lock = new Object();

    public void processBufferedNumber(Integer number) {
        List<String> contentList = generateContent(number);

        List<CompletableFuture<Void>> futures = contentList.stream()
                .map(item -> CompletableFuture.runAsync(() -> addMessageToBuffer(item)))
                .toList();

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
    }

    private void addMessageToBuffer(String message) {
        synchronized (lock) {
            messageBuffer.add(message);
            if (messageBuffer.size() >= BUFFER_SIZE) {
                List<String> messagesToSend = new ArrayList<>(messageBuffer);
                messageBuffer.clear();
                sendMessages(messagesToSend);
            }
        }
    }

    private void sendMessages(List<String> messagesToSend) {
        messagesToSend.forEach(message -> kafkaProducerService.toMSK(message));
    }
}
