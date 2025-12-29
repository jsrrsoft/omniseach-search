package com.omniseach.omniseachsearchservice.dto;

import java.time.LocalDateTime;

public class MessageDto {

    private String id;
    private String content;
    private String senderId;
    private LocalDateTime createdAt;

    public MessageDto(String id, String content, String senderId, LocalDateTime createdAt) {
        this.id = id;
        this.content = content;
        this.senderId = senderId;
        this.createdAt = createdAt;
    }

    public String getId() { return id; }
    public String getContent() { return content; }
    public String getSenderId() { return senderId; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
