package com.omniseach.omniseachsearchservice.dto;

public class SearchResponseDto {

    private Object messages;
    private Object posts;
    private Object photos;

    public SearchResponseDto(Object messages, Object posts, Object photos) {
        this.messages = messages;
        this.posts = posts;
        this.photos = photos;
    }

    public Object getMessages() { return messages; }
    public Object getPosts() { return posts; }
    public Object getPhotos() { return photos; }
}
