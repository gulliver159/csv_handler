package com.aimconsulting.testing.dto;

import com.aimconsulting.testing.model.Result;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultDtoResponse that = (ResultDtoResponse) o;
        return Objects.equals(resultList, that.resultList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resultList);
    }
}
