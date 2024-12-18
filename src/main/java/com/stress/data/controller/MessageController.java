package com.stress.data.controller;

import com.stress.data.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class MessageController {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @PostMapping("/send/{total}")
    public String sendMessage(@PathVariable int total) {
        kafkaProducerService.processNumber(total);

        return "Done";
    }

}

