package com.stress.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.stress.data.config.KafkaConfig.getTopicName;
import static com.stress.data.service.MessageUtils.generateContent;

@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void processNumber(Integer number) {

        List<String> contentList = generateContent(number);

        List<CompletableFuture<Void>> futures = contentList.stream()
            .map(item -> CompletableFuture.runAsync(() -> toMSK(item)))
            .toList();

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
    }

    public void toMSK(String payload) {
        kafkaTemplate.send(getTopicName(), payload);
    }


}
