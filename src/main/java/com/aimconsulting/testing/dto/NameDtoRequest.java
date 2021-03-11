package com.aimconsulting.testing.dto;

public class NameDtoRequest {
    private String name;

    public NameDtoRequest(String name) {
        this.name = name;
    }

    public NameDtoRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
