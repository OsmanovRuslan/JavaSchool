package org.example.service.impl;

import org.example.model.ChatMessage;
import org.example.service.ChatService;
import org.example.socialnetwork.profile.service.ProfileService;

import java.util.*;

public class ChatServiceImpl implements ChatService {

    private final ProfileService profileService;

    private Map<String, List<ChatMessage>> storage = new HashMap<>();

    public ChatServiceImpl(ProfileService profileService) {
        this.profileService = profileService;
    }

    @Override
    public void sendMessage(ChatMessage message) {
        if (message == null || message.getFromUserId() == null || message.getToUserId() == null || message.getMessageId() == null) {
            throw new IllegalArgumentException("Invalid message: messageId, fromUserId, and toUserId are required.");
        }
        if (!profileService.exists(message.getFromUserId()) || !profileService.exists(message.getToUserId())) {
            throw new IllegalArgumentException("Sender or receiver does not exist.");
        }

        String key = generateKey(message.getFromUserId(), message.getToUserId());
        storage.computeIfAbsent(key, k -> new ArrayList<>()).add(message);
    }

    @Override
    public List<ChatMessage> getMessages(Long userId, Long friendId) {
        if (userId == null || friendId == null) {
            throw new IllegalArgumentException("userId and friendId cannot be null.");
        }
        if (!profileService.exists(userId) || !profileService.exists(friendId)) {
            throw new IllegalArgumentException("One of the users does not exist.");
        }
        String key = generateKey(userId, friendId);
        return storage.getOrDefault(key, Collections.emptyList());
    }

    private String generateKey(Long u1, Long u2) {
        return (u1 < u2) ? (u1 + "_" + u2) : (u2 + "_" + u1);
    }
}
