package com.aimconsulting.testing.dto;

public class ContentDtoRequest {
    private String content;

    public ContentDtoRequest(String content) {
        this.content = content;
    }

    public ContentDtoRequest() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
