package com.stress.data.controller;

import com.stress.data.service.KafkaProducerBufferedService;
import com.stress.data.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/")
public class MessageController {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Autowired
    private KafkaProducerBufferedService kafkaProducerBufferedService;

    @PostMapping("/send/{total}")
    public String sendMessage(@PathVariable int total) {
        logStartTime();

        kafkaProducerService.processNumber(total);

        logEndTime();

        return "Done";
    }

    @PostMapping("/sendBuffered/{total}")
    public String sendBufferedMessage(@PathVariable int total) {
        logStartTime();

        kafkaProducerBufferedService.processBufferedNumber(total);

        logEndTime();

        return "Done";
    }

    private void logStartTime() {
        System.out.println("Start time: " + getCurrentTime());
    }

    private void logEndTime() {
        System.out.println("End time: " + getCurrentTime());
    }

    private String getCurrentTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}

