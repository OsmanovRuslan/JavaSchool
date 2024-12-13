package org.example.service.impl;

import org.example.model.ChatMessage;
import org.example.service.ChatService;
import org.example.socialnetwork.profile.service.ProfileService;
import org.example.socialnetwork.profile.service.impl.ProfileServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ChatServiceImplTest {

    @Mock
    private ProfileService profileService;
    private ChatService chatService;

    private static ChatMessage message1;
    private static ChatMessage message2;
    private static ChatMessage message3;

    @BeforeAll
    public static void setUpData() {
        message1 = new ChatMessage(1L, 1L, 2L, "Message1", LocalDateTime.now());
        message2 = new ChatMessage(2L, 2L, 1L, "Message2", LocalDateTime.now());
        message3 = new ChatMessage(3L, 1L, 2L, "Message3", LocalDateTime.now());
    }

    @BeforeEach
    public void init() {
        chatService = new ChatServiceImpl(profileService);
    }

    @Test
    @DisplayName("Test sendMessage Ok")
    void givenMessage_whenSendMessage_thenOk() {
        when(profileService.exists(1L)).thenReturn(true);
        when(profileService.exists(2L)).thenReturn(true);

        chatService.sendMessage(message1);

        verify(profileService, times(2)).exists(anyLong());

        List<ChatMessage> actualMessages = chatService.getMessages(1L, 2L);
        List<ChatMessage> expectedMessages = List.of(message1);

        assertEquals(1, actualMessages.size());
        assertEquals(expectedMessages.get(0), actualMessages.get(0));

    }

    @Test
    @DisplayName("Test sendMessage Exception")
    void givenMessage_whenSendMessage_thenException() {
        when(profileService.exists(1L)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> chatService.sendMessage(message1));
        verify(profileService, times(1)).exists(anyLong());
    }

    @Test
    @DisplayName("Test getMessages Ok")
    void givenId_whenGetMessages_thenOK() {
        when(profileService.exists(1L)).thenReturn(true);
        when(profileService.exists(2L)).thenReturn(true);

        chatService.sendMessage(message1);
        chatService.sendMessage(message2);
        chatService.sendMessage(message3);

        verify(profileService, times(6)).exists(anyLong());

        List<ChatMessage> actualMessages = chatService.getMessages(1L, 2L);
        List<ChatMessage> expectedMessages = List.of(message1, message2, message3);

        assertEquals(expectedMessages.size(), actualMessages.size());
        assertTrue(expectedMessages.containsAll(actualMessages));
    }

    @Test
    @DisplayName("Test getMessages Exception")
    void givenId_whenGetMessages_thenException() {
        when(profileService.exists(1L)).thenReturn(false);
        assertThrows(IllegalArgumentException.class, () -> chatService.getMessages(1L, 2L));
        verify(profileService, times(1)).exists(1L);
    }
}