package com.stress.data.config;

public class KafkaConfig {
    private static String kafkaTopicName = "test-topic";


    public static String getTopicName(){
        return kafkaTopicName;
    }
}
