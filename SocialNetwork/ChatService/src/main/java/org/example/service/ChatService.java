package org.example.service;

import org.example.model.ChatMessage;

import java.util.List;

public interface ChatService {

    void sendMessage(ChatMessage message);

    List<ChatMessage> getMessages(Long userId, Long friendId);

}
