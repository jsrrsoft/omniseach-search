package com.omniseach.omniseachsearchservice.dto;

import java.util.List;

public class PhotoDto {

    private String id;
    private String imageUrl;
    private List<String> tags;

    public PhotoDto(String id, String imageUrl, List<String> tags) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.tags = tags;
    }

    public String getId() { return id; }
    public String getImageUrl() { return imageUrl; }
    public List<String> getTags() { return tags; }
}
