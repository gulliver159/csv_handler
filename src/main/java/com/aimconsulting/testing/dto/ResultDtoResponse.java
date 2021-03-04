package com.aimconsulting.testing.dto;

import java.util.List;

public class ResultDtoResponse {

    private List<ResultInfoDtoResponse> resultList;

    public ResultDtoResponse(List<ResultInfoDtoResponse> resultList) {
        this.resultList = resultList;
    }

    public List<ResultInfoDtoResponse> getResultList() {
        return resultList;
    }

    public void setResultList(List<ResultInfoDtoResponse> resultList) {
        this.resultList = resultList;
    }
}
