package com.websocket.test.controller;

import com.websocket.test.models.Message;
import com.websocket.test.service.MessageService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    private final MessageService messageService;

    public ChatController(MessageService messageService) {
        this.messageService = messageService;
    }

    @MessageMapping("/chat")
    public void processMessage(@Payload Message message) {
        messageService.sendMessage(message.getSender(), message.getReceiver(), message.getBody());
    }
}
