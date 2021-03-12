package com.aimconsulting.testing.model;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return Objects.equals(name, result.name) && Objects.equals(content, result.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, content);
    }

    @Override
    public String toString() {
        return "Result{" +
                "name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
