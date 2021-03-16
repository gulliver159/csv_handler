package com.aimconsulting.testing.repository;

import com.aimconsulting.testing.mapper.ResultMapper;
import com.aimconsulting.testing.model.Result;
import com.aimconsulting.testing.repository_interface.ResultWriter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ResultRepositoryMyBatis implements ResultWriter {

    private final ResultMapper resultMapper;

    public ResultRepositoryMyBatis(ResultMapper resultMapper) {
        this.resultMapper = resultMapper;
    }

    public void deleteAll() {

    }

    public void createResults(List<Result> resultList) {

    }

    public Result getResult(String name) {
        return null;
    }
}
