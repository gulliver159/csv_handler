package com.aimconsulting.testing.repository.impl.mybatis.repository;

import com.aimconsulting.testing.model.Result;
import com.aimconsulting.testing.model.User;
import com.aimconsulting.testing.repository.ResultWriter;
import com.aimconsulting.testing.repository.impl.mybatis.mapper.ResultMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ResultRepositoryMyBatis implements ResultWriter {

    private final ResultMapper resultMapper;

    public ResultRepositoryMyBatis(ResultMapper resultMapper) {
        this.resultMapper = resultMapper;
    }

    public void deleteAll() {
        resultMapper.deleteAll();
    }

    public void deleteResult(String name) {
        resultMapper.deleteResult(name);
    }

    public void createResults(List<Result> resultList) {
        resultMapper.createResults(resultList);
    }

    public List<Result> getResult(String name) {
        return resultMapper.getResult(name);
    }

    public void createResultsByUser(List<Result> resultList) {

    }

    public List<Result> getResultsByUsername(String username) {
        return null;
    }
}
