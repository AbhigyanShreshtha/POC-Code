package com.websocket.test.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class MessageService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final Map<String, String> userTopicMap = new HashMap<>();

    public MessageService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public String getTopic(String user1, String user2) {
        String key1 = user1 + ":" + user2;
        String key2 = user2 + ":" + user1;

        if (userTopicMap.containsKey(key1)) {
            return userTopicMap.get(key1);
        } else if (userTopicMap.containsKey(key2)) {
            return userTopicMap.get(key2);
        } else {
            String topic = UUID.randomUUID().toString();
            userTopicMap.put(key1, topic);
            userTopicMap.put(key2, topic);
            return topic;
        }
    }

    public void sendMessage(String sender, String receiver, String message) {
        String topic = getTopic(sender, receiver);
        kafkaTemplate.send(topic, message);
    }

    @KafkaListener(topics = "default-topic", groupId = "group_id")
    public void listen(String message) {
        // Handle incoming messages here
        System.out.println("Received message: " + message);
    }
}
