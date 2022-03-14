package com.aimconsulting.testing.dto;

import com.aimconsulting.testing.model.User;

public class CreateByUserDtoRequest {
    private String content;
    private User user;

    public CreateByUserDtoRequest() {
    }

    public CreateByUserDtoRequest(String content, User user) {
        this.content = content;
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
