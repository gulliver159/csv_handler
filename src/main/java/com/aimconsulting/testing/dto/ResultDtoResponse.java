package com.aimconsulting.testing.dto;

import com.aimconsulting.testing.model.Result;

import java.util.List;

public class ResultDtoResponse {

    private List<Result> resultList;

    public ResultDtoResponse(List<Result> resultList) {
        this.resultList = resultList;
    }

    public List<Result> getResultList() {
        return resultList;
    }

    public void setResultList(List<Result> resultList) {
        this.resultList = resultList;
    }
}
