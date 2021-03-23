package com.aimconsulting.testing.repository;

import com.aimconsulting.testing.model.Result;
import com.aimconsulting.testing.model.User;

import java.util.List;

public interface ResultWriter {
    void deleteAll();
    void deleteResult(String name);
    void createResults(List<Result> resultList);
    List<Result> getResult(String name);
    void createResultsByUser(List<Result> resultList);
    List<Result> getResultsByUsername(String username);
}
