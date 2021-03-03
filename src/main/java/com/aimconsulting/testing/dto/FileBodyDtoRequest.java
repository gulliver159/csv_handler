package com.aimconsulting.testing.dto;

public class FileBodyDtoRequest {
    private String fileBody;

    public FileBodyDtoRequest(String fileBody) {
        this.fileBody = fileBody;
    }

    public FileBodyDtoRequest() {
    }

    public String getFileBody() {
        return fileBody;
    }

    public void setFileBody(String fileBody) {
        this.fileBody = fileBody;
    }
}
