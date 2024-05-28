package com.websocket.test.controller;

import com.websocket.test.models.Message;
import com.websocket.test.handler.ChatWebSocketHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.WebSocketSession;

import java.util.Set;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatWebSocketHandler chatWebSocketHandler;

    public ChatController(ChatWebSocketHandler chatWebSocketHandler) {
        this.chatWebSocketHandler = chatWebSocketHandler;
    }

    @GetMapping("/sessions")
    public Set<WebSocketSession> getSessions() {
        return chatWebSocketHandler.getSessions();
    }

    @PostMapping("/send")
    public void sendMessage(@RequestBody Message message) {
        chatWebSocketHandler.broadcast(message);
    }
}
