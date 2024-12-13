package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ChatMessage {

    private Long messageId;

    private Long fromUserId;

    private Long toUserId;

    private String content;

    private LocalDateTime timestamp;

}
