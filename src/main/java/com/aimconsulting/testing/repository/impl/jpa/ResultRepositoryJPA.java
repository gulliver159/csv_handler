package com.aimconsulting.testing.repository.impl.jpa;

import com.aimconsulting.testing.model.Result;
import com.aimconsulting.testing.repository.ResultWriter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ResultRepositoryJPA implements ResultWriter {

    private final ResultCrudRepository resultCrudRepository;

    public ResultRepositoryJPA(ResultCrudRepository resultCrudRepository) {
        this.resultCrudRepository = resultCrudRepository;
    }

    public void deleteAll() {

    }

    public void deleteResult(String name) {

    }

    public void createResults(List<Result> resultList) {

    }

    public List<Result> getResult(String name) {
        return null;
    }
}
