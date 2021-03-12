package com.aimconsulting.testing.repository_interface;

import com.aimconsulting.testing.model.Result;

import java.util.List;

public interface ResultWriter {
    void deleteAll();
    void createResults(List<Result> resultList);
    Result getResult(String name);
}
