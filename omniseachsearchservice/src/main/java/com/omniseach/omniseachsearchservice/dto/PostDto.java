package com.omniseach.omniseachsearchservice.dto;

import java.time.LocalDateTime;

public class PostDto {

    private String id;
    private String text;
    private String userId;
    private LocalDateTime createdAt;

    public PostDto(String id, String text, String userId, LocalDateTime createdAt) {
        this.id = id;
        this.text = text;
        this.userId = userId;
        this.createdAt = createdAt;
    }

    public String getId() { return id; }
    public String getText() { return text; }
    public String getUserId() { return userId; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
