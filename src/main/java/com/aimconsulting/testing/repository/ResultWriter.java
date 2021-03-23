package com.aimconsulting.testing.repository;

import com.aimconsulting.testing.model.Result;

import java.util.List;

public interface ResultWriter {
    void deleteAll();
    void deleteResult(String name);
    void createResults(List<Result> resultList);
    List<Result> getResult(String name);
}
