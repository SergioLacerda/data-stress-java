package com.stress.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.stress.data.config.KafkaConfig.getTopicName;
import static com.stress.data.service.MessageUtils.generateContent;
import static com.stress.data.service.MessageUtils.generateRandomContent;

@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void processNumber(Integer total) {

        List<String> contentList = generateContent(total);

        sendContent(contentList);
    }

    public void processRandomizedNumber(int total) {
        List<String> contentList = generateRandomContent(total);

        sendContent(contentList);
    }

    public void toMSK(String payload) {
        kafkaTemplate.send(getTopicName(), payload);
    }

    private void sendContent(List<String> contentList) {
        List<CompletableFuture<Void>> futures = contentList.stream()
                .map(item -> CompletableFuture.runAsync(() -> toMSK(item)))
                .toList();

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
    }
}
