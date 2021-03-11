package com.aimconsulting.testing.model;

public class Result {
    private String name;
    private String content;

    public Result(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public Result() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
