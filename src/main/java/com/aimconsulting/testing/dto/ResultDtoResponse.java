package com.aimconsulting.testing.dto;

import java.util.List;

public class ResultDtoResponse {

    private List<FileInfoDtoResponse> resultList;

    public ResultDtoResponse(List<FileInfoDtoResponse> resultList) {
        this.resultList = resultList;
    }

    public List<FileInfoDtoResponse> getResultList() {
        return resultList;
    }

    public void setResultList(List<FileInfoDtoResponse> resultList) {
        this.resultList = resultList;
    }
}
